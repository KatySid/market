package ru.geekbrains.my.market.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "title")
        private String title;

        @OneToMany(mappedBy = "category")
        private List<Product> products;



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public Category() {
        }

        public Category(String title) {
        this.title = title;
    }

    @Override
        public String toString() {
            return String.format("Category [id = %d, title = %s, products_count = %d]", id, title, products.size());
        }
    }

