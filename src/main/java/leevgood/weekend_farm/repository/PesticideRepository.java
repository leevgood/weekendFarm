package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesticideRepository extends JpaRepository<Pesticide, Long> {
}
