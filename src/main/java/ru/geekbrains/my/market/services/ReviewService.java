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
import ru.geekbrains.my.market.dtos.UserRegDto;
import ru.geekbrains.my.market.models.*;
import ru.geekbrains.my.market.repositories.ReviewRepository;
import ru.geekbrains.my.market.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
   private final UserRepository userRepository;

    public List<Review> findAllByProduct(Product product){
        return reviewRepository.findAllByProduct(product);
    }

    @Transactional
        public void save (Review review) {
        User user = userRepository.findById(review.getUser().getId()).get();
        List<Order> orders = user.getOrders();
        for (Order o : orders) {
            List<OrderItem> items = o.getItems();
            for(OrderItem oi: items){
            if (oi.getProduct().getId().equals(review.getProduct().getId())) {
                reviewRepository.save(review);
                return;
            }
            }
        }
    }



}
