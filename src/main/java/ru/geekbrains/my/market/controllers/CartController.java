package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.services.CartService;
import ru.geekbrains.my.market.utils.Cart;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j

public class CartController {
    private final CartService cartService;


    @GetMapping("/add")
    public void addProductToCart(@RequestParam Long prodId, @RequestParam String cartName) {
        cartService.addProductToCart(cartName, prodId);
    }

    @GetMapping("/delete")
    public void deleteOneProductById(@RequestParam Long prodId,  @RequestParam String cartName) {
        cartService.deleteProductFromCart(cartName, prodId);
    }

    @GetMapping("/deleteAll")
    public void deleteAllProductById(@RequestParam Long prodId,  @RequestParam String cartName) {
        cartService.deleteAllByProduct(cartName, prodId);
    }

    @GetMapping
    public Cart getCart( @RequestParam String cartName){
        return  cartService.getCurrentCart(cartName);
    }

    @GetMapping("/clear")
    public void clearCart( @RequestParam String cartName) {
        cartService.clearCart(cartName);
    }

}
