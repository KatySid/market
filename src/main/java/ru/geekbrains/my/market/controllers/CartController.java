package ru.geekbrains.my.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.utils.Cart;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j

public class CartController {
    private final Cart cart;
    private final ProductService productService;


    @GetMapping("/ping")
    public void ping(@RequestParam Long id) {
        log.info("ping: " + id);
    }

    @GetMapping("/add")
    public void addProduct(@RequestParam Long id) {
        log.info("add: " + id);
        cart.getItems().add(productService.findById(id).get());
    }

    @GetMapping("/delete")
    public void deleteProduct(@RequestParam Long id) {
        log.info("delete product: " + id);
        cart.getItems().remove(productService.findById(id).get());
    }

    @GetMapping
    public List<Product> getAll(){
        return cart.getItems();
    }
    @GetMapping("/clear")
    public void clearCart() {
        log.info("clear cart");
        cart.getItems().clear();
    }

}
