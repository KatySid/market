package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.StringResponse;
import ru.geekbrains.my.market.services.CartService;
import ru.geekbrains.my.market.utils.Cart;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j

public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse creatNewCartId(Principal principal){
            String uuid = null;
            do{
                uuid= UUID.randomUUID().toString();
            } while (cartService.isCartExists(uuid));
            return new StringResponse(uuid);
    }

    @GetMapping("/merge")
    public void mergeCarts(Principal principal, @RequestParam String cartId) {
        cartService.merge(principal.getName(), cartId);
    }

    @GetMapping("/add")
    public void addProductToCart(Principal principal, @RequestParam Long prodId, @RequestParam String cartName) {
        if (principal != null){
            cartName=principal.getName();
        }
        cartService.addProductToCart(cartName, prodId);
    }

    @GetMapping("/delete")
    public void deleteOneProductById(Principal principal, @RequestParam Long prodId,  @RequestParam String cartName) {
        if (principal != null){
            cartName=principal.getName();
        }
        cartService.deleteProductFromCart(cartName, prodId);
    }

    @GetMapping("/deleteAll")
    public void deleteAllProductById(Principal principal, @RequestParam Long prodId,  @RequestParam String cartName) {
        if (principal != null){
            cartName=principal.getName();
        }
        cartService.deleteAllByProduct(cartName, prodId);
    }

    @GetMapping
    public Cart getCart(Principal principal, @RequestParam String cartName){
        if (principal != null){
            cartName=principal.getName();
        }
        return  cartService.getCurrentCart(cartName);
    }

    @GetMapping("/clear")
    public void clearCart(Principal principal, @RequestParam String cartName) {
        if (principal != null){
            cartName=principal.getName();
        }
        cartService.clearCart(cartName);
    }

}
