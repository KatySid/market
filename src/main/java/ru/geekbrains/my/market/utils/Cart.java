package ru.geekbrains.my.market.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.OrderItem;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public void addProductToCart(Long id){
        for(OrderItem oi: items){
            if(oi.getProduct().getId().equals(id)){
                oi.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product product = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist " ));
        items.add(new OrderItem(product));
        recalculate();
    }

    public void clear () {
        items.clear();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public List<OrderItem> getAllItems(){
        return Collections.unmodifiableList(items);
    }

//    public void deleteProduct(Product product){
//        items.remove(product);
//    }
    public void removeProductToCart (Long id) {
        Product product = productService.findById(id).get();
        for (OrderItem oi : items) {
            if (oi.getProduct().equals(product)) {
                items.remove(oi);
                recalculate();
                return;
            }
        }
    }
}
