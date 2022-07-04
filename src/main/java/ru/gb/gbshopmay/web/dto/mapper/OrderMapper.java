package ru.gb.gbshopmay.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gbapimay.order.dto.OrderDto;
import ru.gb.gbapimay.order.dto.OrderItemDto;
import ru.gb.gbshopmay.entity.Order;
import ru.gb.gbshopmay.entity.OrderItem;

@Mapper
public interface OrderMapper {

    OrderItem toOrder(OrderDto orderDto);

    OrderItemDto toOrderDto(Order order);
}
