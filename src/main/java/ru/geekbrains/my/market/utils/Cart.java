package ru.geekbrains.my.market.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

}
