package leevgood.weekend_farm.repository;


import leevgood.weekend_farm.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_itemRepository extends JpaRepository <CartItem, Long> {

}
