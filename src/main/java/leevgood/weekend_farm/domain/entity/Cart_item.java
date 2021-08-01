package leevgood.weekend_farm.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Cart_item {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @Setter
    Cart cart;

    @OneToOne
    @JoinColumn(name = "crops_id")
    @Setter
    CropsItem crops_item;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    @Setter
    Area area;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pesticide_id")
    @Setter
    Pesticide pesticide;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fertilizer_id")
    @Setter
    Fertilizer fertilizer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrients_id")
    @Setter
    Nutrients nutrients;

    @Setter
    int price;
}
