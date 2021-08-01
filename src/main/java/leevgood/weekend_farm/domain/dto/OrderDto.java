package leevgood.weekend_farm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long memberId;
    private int totalCount;
    private int totalPrice;
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDto {
        private Long orderItemId;
        private Long productId;
        private String name;
        private int price;
        private String type;
        private String description;
    }
}
