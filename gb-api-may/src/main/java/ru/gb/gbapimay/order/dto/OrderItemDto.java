package ru.gb.gbapimay.order.dto;

import lombok.*;
import org.apache.tomcat.jni.Address;
import ru.gb.gbapimay.common.enums.OrderStatus;
import ru.gb.gbapimay.product.dto.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Short quantity;
    private BigDecimal itemPrice;
    private BigDecimal totalPrice;
    private ProductDto product;
}