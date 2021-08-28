package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.product.Crops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropsRepository extends JpaRepository<Crops,Long> {
}
