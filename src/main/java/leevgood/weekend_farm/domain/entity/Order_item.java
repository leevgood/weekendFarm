package leevgood.weekend_farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Order_item {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    @Setter
    Orders orders;

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

    @OneToOne(mappedBy = "order_item", cascade = CascadeType.ALL)
    @JsonIgnore
    Crops_progress crops_progress;

    @Setter
    int price;
}
