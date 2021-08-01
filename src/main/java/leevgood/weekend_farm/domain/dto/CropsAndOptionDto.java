package leevgood.weekend_farm.domain.dto;

import leevgood.weekend_farm.domain.entity.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@NoArgsConstructor
public class CropsAndOptionDto {
    private Optional<CropsItem> crops_item;
    private List<Fertilizer> fertilizers;
    private List<Nutrients> nutrientsList;
    private List<Pesticide> pesticides;
    private List<Area> areas;
}
