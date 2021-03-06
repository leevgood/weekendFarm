package leevgood.weekend_farm.service;


import leevgood.weekend_farm.domain.dto.ForCartItemDto;
import leevgood.weekend_farm.domain.entity.*;
import leevgood.weekend_farm.domain.entity.product.Crops;
import leevgood.weekend_farm.domain.entity.product.Product;
import leevgood.weekend_farm.exception.NotFoundException;
import leevgood.weekend_farm.repository.CartRepository;
import leevgood.weekend_farm.repository.MemberRepository;
import leevgood.weekend_farm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;


    public Cart findById(Long cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(()->new EntityNotFoundException("cart not exist. id : " + cartId));
    }

//    @Transactional
//    public CartItem makeCartItem(ProductsDto productsDto, Cart cart){
//        CartItem cartItem = new CartItem();
//        List<Product> products = new ArrayList<>();
//
//        for(Area area:productsDto.getAreas()){
//            products.add((Product)area);
//        }
//
//        for(Crops crops:productsDto.getCrops()){
//            products.add((Product)crops);
//        }
//
//        for(CropsOption cropsOption:productsDto.getCropsOptions()){
//            products.add((Product)cropsOption);
//        }
//
//        cartItem.setProductList(products);
//        cartItem.setPrice(products.stream().mapToInt(p->p.getPrice()).sum());
//        cartItem.setCart(cart);
//
//        return cartItem;
//    }

    @Transactional
    public Long makeCartItem(ForCartItemDto forCartItemDto){

        Member member = memberRepository.findById(forCartItemDto.getMemberId())
                .orElseThrow(() -> new NotFoundException());

        Cart cart = new Cart();

        for (Long productId : forCartItemDto.getProductList()) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException());

            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .price(product.getPrice())
                    .build();

            cart.addCartItem(cartItem);
        }

        member.getCartList().add(cart);
        memberRepository.save(member);
        return cartRepository.save(cart).getId();
    }

    @Transactional
    public void addCartItemAndSave(Cart cart,CartItem cartItem){
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }


    //cartItem?????? orderItem?????? ???????????? ???????????? ???????????? ????????? ?????????.
    @Transactional
    public Order confirmPurchase(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new EntityNotFoundException("cart not exist. id : " + cartId));

        Member member = cart.getMember();

        Order order = new Order();

        OrderItem orderItem;

        List<CartItem> cartItemList = cart.getCartItems();

        //?????? ????????? ????????? ?????? ???????????? ??????(product,price???)
        for(CartItem cartItem : cartItemList){
            orderItem = new OrderItem();

            orderItem.builder()
                    .price(cartItem.getPrice())
                    .product(cartItem.getProduct()).build();
            order.getOrderItems().add(orderItem);
        }

        order.builder()
                .member(cart.getMember())
                .totalPrice(cart.getTotalPrice())
                .build();



        List<OrderItem> orderItems = order.getOrderItems();
        Crops crops = null;
        //???????????? ????????????
        boolean isClear=true;

        //cropsProgress?????? ??? ??????
        for(OrderItem varOrderItem :orderItems){

            Product product = varOrderItem.getProduct();

            crops = product instanceof Crops ? ((Crops) product) : null;


            if(crops==null){
                System.out.println("??????????????? ????????? ????????????.");
                isClear=false;
                break;
            }

            //CropsProgress?????? ??? orderitem??? ??????,,,,
            CropsProgress cropsProgress = new CropsProgress();
            cropsProgress.setCropCondition("no problem");
            cropsProgress.setCultivationStart(LocalDateTime.now().plusDays(7).toString());
            cropsProgress.setExpectedDate(LocalDateTime.now().plusDays(7).plusDays(
                    Long.parseLong(crops.getCultivationPeriod())).toString());
            cropsProgress.setOrder(order);
            order.setCropsProgress(cropsProgress);
        }

        //????????? ?????? ?????? ??? ??????
        member.getOrderList().add(order);
        cartRepository.delete(cart);
        memberRepository.save(member);
        return order;
    }
}
