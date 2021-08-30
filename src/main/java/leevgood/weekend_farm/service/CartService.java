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


    //cartItem들을 orderItem으로 적절하게 변환하고 구매확정 과정을 거친다.
    @Transactional
    public Order confirmPurchase(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new EntityNotFoundException("cart not exist. id : " + cartId));

        Member member = cart.getMember();

        Order order = new Order();

        OrderItem orderItem;

        List<CartItem> cartItemList = cart.getCartItems();

        //카트 아이템 돌면서 오더 아이템에 매핑(product,price만)
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
        //주문확정 성공여부
        boolean isClear=true;

        //cropsProgress생성 후 추가
        for(OrderItem varOrderItem :orderItems){

            Product product = varOrderItem.getProduct();

            crops = product instanceof Crops ? ((Crops) product) : null;


            if(crops==null){
                System.out.println("장바구니에 제품이 없습니다.");
                isClear=false;
                continue;
            }

            //CropsProgress생성 후 orderitem에 추가,,,,
            if (crops != null) {
                CropsProgress cropsProgress = new CropsProgress();
                cropsProgress.setCropCondition("no problem");
                cropsProgress.setCultivationStart(LocalDateTime.now().plusDays(7).toString());
                cropsProgress.setExpectedDate(LocalDateTime.now().plusDays(7).plusDays(
                        Long.parseLong(crops.getCultivationPeriod())).toString());
                cropsProgress.setOrder(order);
                order.setCropsProgress(cropsProgress);
            }
        }

        //멤버에 오더 추가 후 저장
        member.getOrderList().add(order);
        cartRepository.delete(cart);
        memberRepository.save(member);
        return order;
    }

    @Transactional
    public void deleteCart(Long cartId){
        Cart deleteCart = cartRepository.findById(cartId)
                .orElseThrow(()->new EntityNotFoundException("cart not exist. id : " + cartId));
        cartRepository.delete(deleteCart);
    }
}
