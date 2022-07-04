package ru.gb.gbapimay.order.dto;

import lombok.*;
import org.apache.tomcat.jni.Address;
import ru.gb.gbapimay.common.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String recipientFirstname;
    private String recipientLastname;
    private String recipientPhone;
    private String recipientMail;
    private OrderStatus status;
    private LocalDate deliveryDate;
    private BigDecimal deliveryPrice;
    private Address deliveryAddress;
    private Address shopAddress;
    private OrderAccountUserDto accountUser;
    private List<OrderItemDto> orderItems;
}