package leevgood.weekend_farm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForOrderItemDto {
    private Long memberId;
    private List<Long> productList = new ArrayList<>();
}