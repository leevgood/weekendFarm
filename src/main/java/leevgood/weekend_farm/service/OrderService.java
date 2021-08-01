package leevgood.weekend_farm.service;

import javassist.NotFoundException;
import leevgood.weekend_farm.domain.dto.OrderDto;
import leevgood.weekend_farm.domain.entity.CropsItem;
import leevgood.weekend_farm.domain.entity.Member;
import leevgood.weekend_farm.domain.entity.OrderItem;
import leevgood.weekend_farm.domain.entity.Orders;
import leevgood.weekend_farm.repository.Crops_itemRepository;
import leevgood.weekend_farm.repository.MemberRepository;
import leevgood.weekend_farm.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final Crops_itemRepository cropsItemRepository;

    @Transactional
    public Long order(OrderDto orderDto ){

        Member member = memberRepository.findById(orderDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("user not exist. id : " + orderDto.getMemberId()));


        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderDto.OrderItemDto orderItemDto : orderDto.getOrderItemDtoList()) {
            OrderItem orderItem = OrderItem.builder()
                    .price(orderItemDto.getPrice())
                    .build();

            CropsItem cropsItem = cropsItemRepository.findById(orderItemDto.getProductId())
            .orElseThrow(() -> new EntityNotFoundException(""));

            orderItem.setCropsItem(cropsItem);

            orderItems.add(orderItem);
        }


        Orders order = new Orders();
        //order.orders(member, orderItems);

        return orderRepository.save(order).getId();
    }
}
