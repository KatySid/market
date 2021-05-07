package ru.geekbrains.my.market.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public void addProductToCart(Product product){
        items.add(product);
    }

    public void deleteProduct (Product product){
        items.remove(product);
    }

    public void clear () {
        items.clear();
    }

    public List<Product> getAllItems(){
        return Collections.unmodifiableList(items);
    }
}
