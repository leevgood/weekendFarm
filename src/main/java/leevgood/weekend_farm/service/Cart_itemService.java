package leevgood.weekend_farm.service;

import leevgood.weekend_farm.domain.entity.CartItem;
import leevgood.weekend_farm.repository.Cart_itemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Cart_itemService {
    private final Cart_itemRepository cart_itemRepository;

    public List<CartItem> findAll(){
        return cart_itemRepository.findAll();
    }

    public void save(CartItem cart_item){
        cart_itemRepository.save(cart_item);
    }

    public void delete(CartItem cart_item){
        cart_itemRepository.delete(cart_item);
    }
}
