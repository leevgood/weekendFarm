package leevgood.weekend_farm.domain.entity.product;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("crops_option")
public class CropsOption extends Product{
    private String optionType;
}
