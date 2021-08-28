package leevgood.weekend_farm.domain.dto;

import leevgood.weekend_farm.domain.entity.product.Crops;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CropsListDto {
    List<Crops> cropsList = new ArrayList<>();
}
