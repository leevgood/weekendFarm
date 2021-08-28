package leevgood.weekend_farm.domain.dto;

import leevgood.weekend_farm.domain.entity.CropsProgress;
import leevgood.weekend_farm.domain.entity.OrderItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CropsStatusDto {
    private List<OrderItem> orderItems = new ArrayList<>();
    private CropsProgress cropsProgress;

    @Builder
    public CropsStatusDto(List<OrderItem> orderItems, CropsProgress cropsProgress){
        this.orderItems = orderItems;
        this.cropsProgress = cropsProgress;
    }
}
