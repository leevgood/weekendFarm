package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
}
