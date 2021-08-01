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
public class Nutrients {

    @Id
    @GeneratedValue
    @Column(name = "nutrients_id")
    Long id;

    String nutrients_name;
    int nutrients_price;
    String nutrients_description;

    LocalDateTime create_date;
    LocalDateTime update_date;

}
