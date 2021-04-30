package ru.geekbrains.my.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.my.market.models.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

}
