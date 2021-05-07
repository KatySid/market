package ru.geekbrains.my.market.dtos;

import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDto {
    private List<ProductDto> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public CartDto (Cart cart){
        this.items = cart.getAllItems().stream().map(ProductDto :: new).collect(Collectors.toList());
    }

    public List<ProductDto> getAllItems(){
        return Collections.unmodifiableList(items);
    }
}
