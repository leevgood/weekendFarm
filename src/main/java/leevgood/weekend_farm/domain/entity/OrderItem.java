package leevgood.weekend_farm.domain.entity;

import lombok.*;

import javax.persistence.*;

//@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long id;

    @OneToOne
    @Setter
    private CropsItem cropsItem;

    private int price;


    @Builder
    public OrderItem(int price) {
        this.price = price;
    }
}
