package ru.geekbrains.my.market.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.OrderItem;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public void addProductToCart(Long id){
        for(OrderItem oi: items){
            if(oi.getProduct().getId().equals(id)){
                oi.incrementQuantity();
                return;
            }
        }
        Product product = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist " ));
        items.add(new OrderItem(product));
    }

//    public void deleteProduct (Product product){
//        Product p = null;
//        for (int i = 0; i < items.size(); i++) {
//            if(product.getId()==items.get(i).getId()){
//                p=items.get(i);
//                break;
//            }
//        }
//        if(p!=null){
//            items.remove(p);
//        }
//    }

    public void clear () {
        items.clear();
    }

    public List<OrderItem> getAllItems(){
        return Collections.unmodifiableList(items);
    }
}
