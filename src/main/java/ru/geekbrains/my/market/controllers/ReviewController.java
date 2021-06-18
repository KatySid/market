package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.ReviewDto;
import ru.geekbrains.my.market.dtos.UserDto;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.models.Review;
import ru.geekbrains.my.market.models.User;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.services.ReviewService;
import ru.geekbrains.my.market.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

//    @GetMapping
//    public Review getReview(){
//        Review review = new Review();
//        review.setTitle("title");
//        return review;
   // }

    @GetMapping
    public List<ReviewDto> getReviewProduct(@RequestParam Long productId){
        Product product = productService.findById(productId).get();
        return reviewService.findAllByProduct(product).stream().map(ReviewDto::new).collect(Collectors.toList());
    }

//    @GetMapping
//    public List<ReviewDto> getReviewsProduct(@RequestParam Long productId){
//
////
////        return reviews;
//        ;Product product = productService.findById(1l).get()
////        System.out.println(product.toString());
//        //List<Review> reviews = reviewService.findAllByProduct(product);
//
//        return reviewService.findAllByProduct(product).stream().map(ReviewDto::new).collect(Collectors.toList());
//    }

    @PostMapping
    public void createNewReview(Principal principal, @RequestParam String comment, @RequestParam Long productId) {
        Review review = new Review();
        review.setUser(userService.findByUsername(principal.getName()).get());
        review.setProduct(productService.findById(productId).get());
        review.setTitle(comment);
        reviewService.save(review);
    }

//    @GetMapping
//    @Transactional
//    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
//        User user = userService.findByUsername(principal.getName()).get();
//        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
//    }
}
