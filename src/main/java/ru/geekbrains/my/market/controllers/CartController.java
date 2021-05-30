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


    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable (name = "productId") Long id) {
        cartService.addProductToCart(id);
    }

    @GetMapping("/delete/{productId}")
    public void deleteOneProductById(@PathVariable (name = "productId") Long id) {
        cartService.deleteProductFromCart(id);
    }

//    @GetMapping("/add/{productId}")
//    public void addToCart(@PathVariable(name = "productId") Long id) {
//        Optional<Product> product = productService.findById(id);
//        cartService.addToCart(id);
//    }

    @GetMapping
    public CartDto getCart(){
        return  new CartDto(cart);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

//    @GetMapping("/delete/{id}")
//    public void deleteAllByProduct(@PathVariable Long id) {
//        cartService.deleteAllByProduct(id);
//    }





    @GetMapping("/decrement")
    public void deleteProductFromCart(@RequestParam Long id) {
        cartService.deleteProductFromCart(id);
    }

    @GetMapping("/increment")
    public void incrementProductQuantity(@RequestParam Long id) {
        cartService.addProductToCart(id);
    }

}
