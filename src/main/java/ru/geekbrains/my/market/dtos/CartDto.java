package ru.geekbrains.my.market.dtos;
import ru.geekbrains.my.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();

    }

    public CartDto (Cart cart){
        this.items = cart.getAllItems().stream().map(OrderItemDto :: new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }

    public List<OrderItemDto> getItems(){
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getSum(){
        return sum;
    }
}
