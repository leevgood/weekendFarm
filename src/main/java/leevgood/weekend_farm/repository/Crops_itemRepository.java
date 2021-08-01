package leevgood.weekend_farm.repository;

import leevgood.weekend_farm.domain.entity.CropsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Crops_itemRepository extends JpaRepository<CropsItem,Long>{

    List<CropsItem> findAll();

}
