package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.product.CropsOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropsOptionRepository extends JpaRepository<CropsOption,Long> {
}
