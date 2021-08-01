package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Order_item;
import leevgood.weekend_farm.repository.Order_itemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Order_itemService {
    private final Order_itemRepository order_itemRepository;

    public List<Order_item> findAll(){
        return order_itemRepository.findAll();
    }

    public void save(Order_item order_item){
        order_itemRepository.save(order_item);
    }

    public List<Order_item> findOrder_itemsFetchJoin(Long id){
        return order_itemRepository.findOrder_itemFetchJoin(id);
    };

}
