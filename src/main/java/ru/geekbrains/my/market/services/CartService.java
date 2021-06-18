package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    public void addProductToCart(String cartId, Long productId) {
        Cart cart = getCurrentCart(cartId);
        if(cart.addToCart(productId)){
            save(cartId, cart);
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.addProductToCart(product);
        save(cartId, cart);
    }

    public void deleteProductFromCart(String cartId, Long productId){
        Cart cart = getCurrentCart(cartId);
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.deleteProductFromCart(product);
        save(cartId, cart);
    }

    public void deleteAllByProduct(String cartId, Long productId){
        Cart cart = getCurrentCart(cartId);
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist "));
        cart.deleteAllByProduct(product);
        save(cartId, cart);
    }

    public void clearCart(String cartId){
        Cart cart= getCurrentCart(cartId);
        cart.clear();
        save(cartId, cart);
    }

    public void save(String cartId, Cart cart) {
        redisTemplate.opsForValue().set("april_cart_" + cartId, cart);
    }

    public Cart getCurrentCart(String cartId){
        if (!redisTemplate.hasKey("april_cart_" + cartId)) {
            redisTemplate.opsForValue().set("april_cart_" + cartId, new Cart());
        }
        Cart cart = (Cart)redisTemplate.opsForValue().get("april_cart_" + cartId);
        return cart;
    }

    public boolean isCartExists(String cartId) {
        return redisTemplate.hasKey("april_cart_" + cartId);
    }

    public void merge(String userCartId, String guestCartId) {
        Cart userCart = getCurrentCart(userCartId);
        Cart guestCart = getCurrentCart(guestCartId);
        userCart.merge(guestCart);
        save(userCartId, userCart);
        save(guestCartId, guestCart);
    }
}
