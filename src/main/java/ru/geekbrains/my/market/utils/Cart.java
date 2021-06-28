package ru.geekbrains.my.market.utils;

import lombok.Data;
import ru.geekbrains.my.market.dtos.OrderItemDto;
import ru.geekbrains.my.market.models.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    public Cart(){
        items = new ArrayList<>();
        sum = BigDecimal.ZERO;

    }

    public boolean addToCart(Long id){
        for(OrderItemDto oi: items) {
            if (oi.getProductId().equals(id)) {
                oi.incrementQuantity();
                recalculate();
                return true;
            }
        }
        return false;

    }

    public void addProductToCart(Product product){
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void deleteProductFromCart(Product product){
        for(OrderItemDto oi: items){
            if(oi.getProductId().equals(product.getId())){
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
        for(OrderItemDto oi: items){
            if(oi.getProductId().equals(product.getId())){
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
        for (OrderItemDto oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }

}
