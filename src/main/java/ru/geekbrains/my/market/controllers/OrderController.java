package ru.geekbrains.my.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.OrderDto;
import ru.geekbrains.my.market.models.User;
import ru.geekbrains.my.market.services.OrderService;
import ru.geekbrains.my.market.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createNewOrder(Principal principal, @RequestBody OrderDto orderDto) {
        User user = userService.findByUsername(principal.getName()).get();
        orderService.createOrderForCurrentUser(user, orderDto.getAdress(),orderDto.getPhoneNumber());
    }

    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
