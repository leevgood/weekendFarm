package leevgood.weekend_farm.mapper;

import leevgood.weekend_farm.domain.dto.OrderDto;
import leevgood.weekend_farm.domain.entity.OrderItem;

public class OrderItemMapper implements BaseMapper<OrderItem, OrderDto.OrderItemDto> {

    @Override
    public OrderItem toEntity(OrderDto.OrderItemDto dto) {
        return OrderItem.builder()
                .price(dto.getPrice())
                .build();
    }

    @Override
    public OrderDto.OrderItemDto toDto(OrderItem entity) {
        return null;
    }
}
