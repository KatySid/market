package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.ProductDto;
import ru.geekbrains.my.market.error_handling.MarketError;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Category;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.CategoryService;
import ru.geekbrains.my.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

//    @GetMapping
//    public List<ProductDto> getAll(){
//        return productService.findAll().stream().map(ProductDto :: new).collect(Collectors.toList());
//    }
    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page) {
        Page<Product> productsPage = productService.findPage(page - 1, 10);
        Page<ProductDto> dtoPage = new PageImpl<>(productsPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productsPage.getPageable(), productsPage.getTotalElements());
        return dtoPage;
    }

    @GetMapping ("/{id}")
    public ProductDto getOneProductById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist " + id));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        return productService.createNewProduct(productDto);
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
