package ru.geekbrains.my.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.my.market.models.Review;


@Repository
public interface ReviewRepository extends JpaRepository <Review, Long>, JpaSpecificationExecutor<Review> {

    //Page<Review> findAllByProduct(Pageable pageable);

}
