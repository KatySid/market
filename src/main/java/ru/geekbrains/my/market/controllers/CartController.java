package ru.geekbrains.my.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.CartDto;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Order;
import ru.geekbrains.my.market.models.User;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.services.UserService;
import ru.geekbrains.my.market.utils.Cart;

import java.util.List;

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


//    @GetMapping("/delete")
//    public void deleteProduct(@RequestParam Long id) {
//        log.info("delete product: " + id);
//        cart.deleteProduct(productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist " + id)));
//
//    }

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
