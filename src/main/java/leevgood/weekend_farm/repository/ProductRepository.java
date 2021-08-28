package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
