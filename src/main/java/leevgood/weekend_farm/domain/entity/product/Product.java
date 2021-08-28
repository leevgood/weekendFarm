package leevgood.weekend_farm.domain.entity.product;

import leevgood.weekend_farm.domain.entity.CartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;

    private int count;

    private String description;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
