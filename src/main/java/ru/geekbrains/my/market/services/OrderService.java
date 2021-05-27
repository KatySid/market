package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.models.*;
import ru.geekbrains.my.market.repositories.OrderRepository;
import ru.geekbrains.my.market.utils.Cart;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createNewOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        order.setItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public Order createOrderForCurrentUser(User user, String adress, String phone) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        order.setItems(cart.getItems());
        order.setAdress(adress);
        order.setPhoneNumber(phone);
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }


}
