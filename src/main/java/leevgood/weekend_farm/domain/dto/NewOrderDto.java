package leevgood.weekend_farm.domain.dto;

import leevgood.weekend_farm.domain.entity.CartItem;
import leevgood.weekend_farm.domain.entity.OrderItem;

import java.util.List;

public class NewOrderDto {
    private Long id;
    private Long memberId;
    private int totalPrice;
    private List<OrderItem> orderItems;
}
