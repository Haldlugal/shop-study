package ru.gb.gbshopmay.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("Создан"),
    DELIVERED("Доставлен"),
    RECEIVED("Получен"),
    CANCELED("Отменем"),
    PROCESSING("В обработке");

    private final String status;
}