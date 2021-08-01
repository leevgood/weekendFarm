package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.Cart_item;
import leevgood.weekend_farm.repository.Cart_itemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Cart_itemService {
    private final Cart_itemRepository cart_itemRepository;

    public List<Cart_item> findAll(){
        return cart_itemRepository.findAll();
    }

    public void save(Cart_item cart_item){
        cart_itemRepository.save(cart_item);
    }

    public void delete(Cart_item cart_item){
        cart_itemRepository.delete(cart_item);
    }
}
