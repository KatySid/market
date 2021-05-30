package ru.geekbrains.my.market.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.my.market.models.OrderItem;
import ru.geekbrains.my.market.models.Product;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
@Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {
    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public void addProductToCart(Product product){
        for(OrderItem oi: items){
            if(oi.getProduct().getId().equals(product.getId())){
                oi.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(product));
        recalculate();
    }

    public void deleteProductFromCart(Product product){
        for(OrderItem oi: items){
            if(oi.getProduct().getId().equals(product.getId())){
                oi.decrementQuantity();
                if (oi.getQuantity()==0){
                    deleteAllByProduct(product);
                }
                recalculate();
                return;
            }
        }
    }

    public void deleteAllByProduct(Product product){
        for(OrderItem oi: items){
            if(oi.getProduct().equals(product)){
                items.remove(oi);
                recalculate();
                return;
            }
        }
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

}
