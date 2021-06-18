package ru.geekbrains.my.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.models.Review;
import ru.geekbrains.my.market.models.User;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ReviewDto {
    private  Long id;
    private  String title;
    private String userName;


    public ReviewDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        User user = review.getUser();
        this.userName = user.getUsername();
    }
}
