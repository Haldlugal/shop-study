package ru.gb.gbapimay.order.dto;

import lombok.*;
import org.apache.tomcat.jni.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderAccountUserDto {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}