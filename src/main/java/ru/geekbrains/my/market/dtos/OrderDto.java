package ru.geekbrains.my.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.models.Order;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private BigDecimal price;
    private String adress;
    private String phoneNumber;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.adress = order.getAdress();
        this.phoneNumber= order.getPhoneNumber();
        this.description = order.getItems().stream().map(o -> o.getProduct().getTitle() + " x" + o.getQuantity()).collect(Collectors.joining(", "));
    }
}
