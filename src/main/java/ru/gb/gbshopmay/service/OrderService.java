package ru.gb.gbshopmay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gbapimay.order.dto.OrderDto;
import ru.gb.gbapimay.order.dto.OrderItemDto;
import ru.gb.gbshopmay.dao.OrderDao;
import ru.gb.gbshopmay.entity.Order;
import ru.gb.gbshopmay.entity.OrderItem;
import ru.gb.gbshopmay.web.dto.mapper.OrderMapper;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    public boolean save(Order order) {
        orderDao.save(order);
        return true;
    }
}
