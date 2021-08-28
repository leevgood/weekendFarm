package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
