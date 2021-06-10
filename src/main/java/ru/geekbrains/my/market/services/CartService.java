package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.dtos.CartDto;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final Cart cart;

    public void addProductToCart(Long id) {
        if(cart.addToCart(id)){
            return;
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.addProductToCart(product);
    }

    public void deleteProductFromCart(Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.deleteProductFromCart(product);
    }

    public void deleteAllByProduct(Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.deleteAllByProduct(product);
    }

    public void clearCart(){
        cart.clear();
    }

    public CartDto getCartDto(){
        return new CartDto(cart);
    }

}
