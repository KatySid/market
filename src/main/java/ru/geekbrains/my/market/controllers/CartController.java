package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.CartDto;
import ru.geekbrains.my.market.services.CartService;
import ru.geekbrains.my.market.utils.Cart;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j

public class CartController {
    private final Cart cart;
    private final CartService cartService;


    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        log.info("add: " + id);
        cartService.addProductToCart(id);
    }

    @GetMapping
    public CartDto getCart(){
        return  new CartDto(cart);
    }

    @GetMapping("/clear")
    public void clearCart() {
        log.info("clear cart");
        cart.clear();
    }

}
