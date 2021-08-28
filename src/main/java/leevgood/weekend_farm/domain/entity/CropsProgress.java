package leevgood.weekend_farm.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CropsProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crops_progress_id")
    private Long id;

    @OneToOne(mappedBy = "cropsProgress")
    @Setter
    private Order order;

    @Setter
    private String cultivationStart;

    @Setter
    private String expectedDate;

    @Setter
    private String cropCondition;
}
