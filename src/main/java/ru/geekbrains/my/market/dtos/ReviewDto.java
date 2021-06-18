package ru.geekbrains.my.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.models.Review;
import ru.geekbrains.my.market.models.User;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ReviewDto {
    private  Long id;
    private  String title;
    private String userName;
    private String createdAt;


    public ReviewDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        User user = review.getUser();
        this.userName = user.getUsername();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" )).replace("T", " ");
    }
}
