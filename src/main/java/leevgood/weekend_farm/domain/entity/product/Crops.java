package leevgood.weekend_farm.domain.entity.product;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("crops")
@Getter
public class Crops extends Product{

    private String startTimeAvailable;
    private String cultivationPeriod;

}
