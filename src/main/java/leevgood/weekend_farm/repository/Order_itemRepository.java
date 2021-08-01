package leevgood.weekend_farm.repository;


import leevgood.weekend_farm.domain.entity.Order_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Order_itemRepository extends JpaRepository <Order_item, Long> {

    @Query("select ot from Order_item ot join fetch ot.crops_progress where ot.orders.member.id=:id")
    List<Order_item> findOrder_itemFetchJoin(@Param("id") Long id);

}
