package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    @GetMapping
    public Page<ReviewDto> getReviewProduct(@RequestParam Long productId, @RequestParam (name = "p") int page){
        Product product = productService.findById(productId).get();
        if(page<1){
            page=1;
        }
        int pageSize = 10;
        Page<Review> reviewsPage = reviewService.findAllByProduct(product,page, pageSize);
        Page<ReviewDto> dtoPage = new PageImpl<>(reviewsPage.getContent().stream().map(ReviewDto::new).collect(Collectors.toList()), reviewsPage.getPageable(), reviewsPage.getTotalElements());
        return dtoPage;
    }

    @PostMapping
    public void createNewReview(Principal principal, @RequestParam String comment, @RequestParam Long productId) {
        Review review = new Review();
        review.setUser(userService.findByUsername(principal.getName()).get());
        review.setProduct(productService.findById(productId).get());
        review.setTitle(comment);
        reviewService.save(review);
    }
}
