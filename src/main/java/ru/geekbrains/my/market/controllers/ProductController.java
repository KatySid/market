package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.ProductService;

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
    public Product createNewProduct(@RequestBody Product product){
        return productService.save(product);
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
