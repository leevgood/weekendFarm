package leevgood.weekend_farm.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CropsItem {

    @Id
    @GeneratedValue
    @Column(name = "crops_item_id")
    Long id;

    int price;

    String crops_name;
    String type;
    String start_time_available;
    String cultivation_period;

}
