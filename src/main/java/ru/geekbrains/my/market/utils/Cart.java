package ru.geekbrains.my.market.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.dtos.ProductDto;
import ru.geekbrains.my.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Cart {
    private List<ProductDto> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public void addProductToCart(ProductDto product){
        items.add(product);
    }

    public void deleteProduct (ProductDto product){
        items.remove(product);
    }

    public void clear () {
        items.clear();
    }

    public List<ProductDto> getAllItems(){
        return Collections.unmodifiableList(items);
    }
}
