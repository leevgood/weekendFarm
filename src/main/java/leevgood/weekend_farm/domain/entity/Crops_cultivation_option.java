package leevgood.weekend_farm.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crops_cultivation_option {

    @Id
    @GeneratedValue
    @Column(name = "crops_cultivation_option_id")
    Long id;

    String pesticide;
    String fertilizer;
    String nutrients;
}
