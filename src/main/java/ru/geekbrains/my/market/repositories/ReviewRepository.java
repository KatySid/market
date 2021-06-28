package ru.geekbrains.my.market.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.models.Review;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository <Review, Long>, JpaSpecificationExecutor<Review> {

    Page<Review> findAllByProduct(Product product, Pageable pageable);

}
