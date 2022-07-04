package ru.gb.gbshopmay.web.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gb.gbapimay.order.dto.OrderDto;
import ru.gb.gbapimay.order.dto.OrderItemDto;
import ru.gb.gbapimay.order.dto.OrderItemDto.OrderItemDtoBuilder;
import ru.gb.gbshopmay.entity.Order;
import ru.gb.gbshopmay.entity.OrderItem;
import ru.gb.gbshopmay.entity.OrderItem.OrderItemBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T16:23:59+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderItem toOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderItemBuilder orderItem = OrderItem.builder();

        return orderItem.build();
    }

    @Override
    public OrderItemDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderItemDtoBuilder orderItemDto = OrderItemDto.builder();

        return orderItemDto.build();
    }
}
