package ru.geekbrains.my.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.CartDto;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.services.UserService;
import ru.geekbrains.my.market.utils.Cart;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j

public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final UserService userService;


    @GetMapping("/add")
    public void addProduct(@RequestParam Long id) {
        log.info("add: " + id);
        cart.addProductToCart(id);
    }

    @GetMapping
    public CartDto getAll(){
        return  new CartDto(cart);
    }

    @GetMapping("/clear")
    public void clearCart() {
        log.info("clear cart");
        cart.clear();
    }

}
