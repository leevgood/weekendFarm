package leevgood.weekend_farm.domain.entity.product;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("area")
public class Area extends Product{
    String areaType;
}
