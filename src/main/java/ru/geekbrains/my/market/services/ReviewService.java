package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbrains.my.market.dtos.ProductDto;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.models.Review;
import ru.geekbrains.my.market.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CategoryService categoryService;

//    @Transactional
//    public Review createNewReview(@RequestBody Review review) {
//        return reviewRepository.save(review);
//    }




}
