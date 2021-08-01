package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
