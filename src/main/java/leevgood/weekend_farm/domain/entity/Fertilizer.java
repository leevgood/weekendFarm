package leevgood.weekend_farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fertilizer {

    @Id
    @GeneratedValue
    @Column(name = "fertilizer_id")
    Long id;

    String fertilizer_name;
    int fertilizer_price;
    String fertilizer_description;

    LocalDateTime create_date;
    LocalDateTime update_date;

}
