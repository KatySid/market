package ru.geekbrains.my.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.my.market.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findOneByTitle(String title);
}
