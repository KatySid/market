package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.models.Review;
import ru.geekbrains.my.market.services.ReviewService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public Review getReview(){
        Review review = new Review();
        review.setTitle("no comment");
        return review;
    }

//    @PostMapping
//    public void createNewReview(Principal principal, @RequestBody OrderDto orderDto) {
//        User user = userService.findByUsername(principal.getName()).get();
//        orderService.createOrderForCurrentUser(user, orderDto.getAdress(),orderDto.getPhoneNumber());
//    }

//    @GetMapping
//    @Transactional
//    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).get();
//        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
//    }
}
