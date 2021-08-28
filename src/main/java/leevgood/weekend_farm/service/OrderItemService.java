package leevgood.weekend_farm.service;


import leevgood.weekend_farm.domain.entity.OrderItem;
import leevgood.weekend_farm.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll(){
        return orderItemRepository.findAll();
    }

    public void save(OrderItem order_item){
        orderItemRepository.save(order_item);
    }


}
