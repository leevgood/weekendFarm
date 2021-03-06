package leevgood.weekend_farm.service;

import leevgood.weekend_farm.controller.CropsStatus;
import leevgood.weekend_farm.domain.dto.*;
import leevgood.weekend_farm.domain.entity.*;
import leevgood.weekend_farm.domain.entity.product.Area;
import leevgood.weekend_farm.domain.entity.product.Crops;
import leevgood.weekend_farm.domain.entity.product.CropsOption;
import leevgood.weekend_farm.domain.entity.product.Product;
import leevgood.weekend_farm.exception.NotFoundException;
import leevgood.weekend_farm.repository.MemberRepository;
import leevgood.weekend_farm.repository.OrderRepository;
import leevgood.weekend_farm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Order orderDtoToOrder(NewOrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        return order;
    }

/*    @Transactional
    public OrderItem makeOrderItem(ProductsDto productsDto, Order order){
        OrderItem orderItem = new OrderItem();
        List<Product> products = new ArrayList<>();

        for(Area area:productsDto.getAreas()){
            products.add((Product)area);
        }

        for(Crops crops:productsDto.getCrops()){
            products.add((Product)crops);
        }

        for(CropsOption cropsOption:productsDto.getCropsOptions()){
            products.add((Product)cropsOption);
        }
*//*

        orderItem.setProductList(products);
*//*
        orderItem.setPrice(products.stream().mapToInt(p->p.getPrice()).sum());
        orderItem.setOrder(order);

        //cropsProgress????????????
        Crops crops = productsDto.getCrops().get(0);
        CropsProgress cropsProgress = new CropsProgress();
        cropsProgress.setCropCondition("no problem");
        cropsProgress.setCultivationStart(LocalDateTime.now().plusDays(7).toString());
        cropsProgress.setExpectedDate(LocalDateTime.now().plusDays(7).plusDays(
                Long.parseLong(crops.getCultivationPeriod())).toString());
*//*
        cropsProgress.setOrderItem(orderItem);
*//*
        //????????????
*//*

        orderItem.setCropsProgress(cropsProgress);
*//*

        return orderItem;
    }*/

    @Transactional
    public Long makeOrderItem(ForOrderItemDto forOrderItemDto) {
        Member member = memberRepository.findById(forOrderItemDto.getMemberId())
                .orElseThrow(() -> new NotFoundException());

        Order order = new Order();

        for (Long productId : forOrderItemDto.getProductList()) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException());

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .price(product.getPrice())
                    .build();

            order.addOrderItem(orderItem);
        }

        member.getOrderList().add(order);
        memberRepository.save(member);
        return orderRepository.save(order).getId();
    }


    @Transactional
    public Long order(OrderDto orderDto) {

        Member member = memberRepository.findById(orderDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("user not exist. id : " + orderDto.getMemberId()));


        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderDto.OrderItemDto orderItemDto : orderDto.getOrderItemDtoList()) {
            OrderItem orderItem = OrderItem.builder()
                    .price(orderItemDto.getPrice())
                    .build();


            orderItems.add(orderItem);
        }


        Order order = new Order();
        //order.order(member, orderItems);

        return orderRepository.save(order).getId();
    }

    //memberId??? ????????? ????????? ?????? ???????????? ?????? ???????????? ????????? ????????????.
    @Transactional
    public void addOrderItemAndSave(Order order, OrderItem orderItem) {
        /*        order.setOrderItem(orderItem);
         */
        orderRepository.save(order);
    }

    public List<CropsStatusDto> getCropsStatus(Long memberId) {
        Member member = memberRepository.getById(memberId);
        List<Order> orderList = member.getOrderList();
        List<CropsStatusDto> cropsStatusDtos = new ArrayList<>();

        CropsStatusDto cropsStatusDto;

        for (Order order : orderList) {
            cropsStatusDto  = new CropsStatusDto();
            cropsStatusDto.builder()
                    .cropsProgress(order.getCropsProgress())
                    .orderItems(order.getOrderItems());

            cropsStatusDtos.add(cropsStatusDto);
        }

        return cropsStatusDtos;
    }
}
