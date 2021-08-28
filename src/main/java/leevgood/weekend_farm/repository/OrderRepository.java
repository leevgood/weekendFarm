package leevgood.weekend_farm.repository;


import leevgood.weekend_farm.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
