package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.error_handling.MarketError;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping ("/{id}")
    public Product getOneProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length() < 3) {
            errors.add("Too short title");
        }
        if (product.getPrice() < 1) {
            errors.add("Invalid product price");
        }
        if (errors.size() > 0) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.save(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @PutMapping
    public Product updateProduct (@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteAll(){
        productService.deleteAll();
    }

    @DeleteMapping ("/{id}")
    public Product deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }
}
