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
public class Pesticide {

    @Id
    @GeneratedValue
    @Column(name = "pesticide_id")
    Long id;

    String pesticide_name;
    int pesticide_price;
    String pesticide_description;

    LocalDateTime create_date;
    LocalDateTime update_date;

}
