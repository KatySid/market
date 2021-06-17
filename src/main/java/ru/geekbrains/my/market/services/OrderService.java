package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.dtos.OrderItemDto;
import ru.geekbrains.my.market.models.*;
import ru.geekbrains.my.market.repositories.OrderRepository;
import ru.geekbrains.my.market.utils.Cart;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;


    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

//    public Order createNewOrder(User user) {
//        Order order = new Order();
//        order.setUser(user);
//        Cart cart = cartService.getCurrentCart();
//        order.setPrice(cart.getSum());
//        order.setItems(cart.getItems());
//        for (OrderItem oi : cart.getItems()) {
//            oi.setOrder(order);
//        }
//        order = orderRepository.save(order);
//        cart.clear();
//        cartService.save(cart);
//        return order;
//    }

    public Order createOrderForCurrentUser(User user, String adress, String phone) {
        Order order = new Order();
        order.setUser(user);
        order.setPhoneNumber(phone);
        order.setAdress(adress);
        Cart cart = cartService.getCurrentCart("cart"); // todo ERROR
        order.setPrice(cart.getSum());
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            order.getItems().add(orderItem);
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setPrice(o.getPrice());
            orderItem.setProduct(productService.findById(o.getProductId()).get());
        }
        order = orderRepository.save(order);
        cart.clear();
        cartService.save("cart", cart); // todo ERROR
        return order;
    }


}
