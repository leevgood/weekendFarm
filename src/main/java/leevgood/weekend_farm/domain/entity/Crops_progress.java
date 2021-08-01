package leevgood.weekend_farm.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Crops_progress {

    @Id
    @GeneratedValue
    @Column(name = "crops_progress_id")
    Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    @Setter
    Order_item order_item;

    @Setter
    String cultivation_start;

    @Setter
    String expected_date;

    @Setter
    String crop_condition;
}
