package leevgood.weekend_farm.domain.dto;

import leevgood.weekend_farm.domain.entity.product.Area;
import leevgood.weekend_farm.domain.entity.product.Crops;
import leevgood.weekend_farm.domain.entity.product.CropsOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsForCropDto {
    private Crops crops;
    private List<Area> areas;
    private List<CropsOption> cropsOptions;
}
