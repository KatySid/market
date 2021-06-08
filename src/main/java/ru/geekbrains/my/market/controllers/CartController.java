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
    private final CartService cartService;


    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable (name = "productId") Long id) {
        cartService.addProductToCart(id);
    }

    @GetMapping("/delete/{productId}")
    public void deleteOneProductById(@PathVariable (name = "productId") Long id) {
        cartService.deleteProductFromCart(id);
    }

    @GetMapping("/deleteAll/{productId}")
    public void deleteAllProductById(@PathVariable (name = "productId") Long id) {
        cartService.deleteAllByProduct(id);
    }

    @GetMapping
    public CartDto getCart(){
        return  cartService.getCartDto();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

}
