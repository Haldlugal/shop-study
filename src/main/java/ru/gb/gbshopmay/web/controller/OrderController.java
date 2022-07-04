package ru.gb.gbshopmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbapimay.order.dto.OrderDto;
import ru.gb.gbshopmay.entity.Order;
import ru.gb.gbshopmay.entity.enums.OrderStatus;
import ru.gb.gbshopmay.entity.security.AccountUser;
import ru.gb.gbshopmay.service.CartService;
import ru.gb.gbshopmay.service.OrderService;
import ru.gb.gbshopmay.web.model.Cart;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/fill")
    public String fill(Model model, HttpSession httpSession) {
        Cart cart = cartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        model.addAttribute("orderItemDto", new OrderDto());
        return "order/order-form";
    }

    @PostMapping("/submit")
    public String submit(OrderDto orderItemDto, HttpSession httpSession) {
        AccountUser principal = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartService.getCurrentCart(httpSession);
        Order order = Order
                .builder()
                .recipientFirstname(orderItemDto.getRecipientFirstname())
                .recipientLastname(orderItemDto.getRecipientLastname())
                .recipientPhone(orderItemDto.getRecipientPhone())
                .recipientMail(orderItemDto.getRecipientMail())
                .createdDate(LocalDateTime.now())
                .deliveryPrice(BigDecimal.valueOf(0))
                .deliveryDate(LocalDate.now())
                .status(OrderStatus.CREATED)
                .orderItems(cart.getItems())
                .accountUser(principal)
                .build();
        order.setPrice(cart.getTotalPrice());
        orderService.save(order);
        System.out.println(orderItemDto.getRecipientFirstname());
        return "order/submit-success";
    }
}