package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.my.market.dtos.ProductDto;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.repositories.specification.ProductSpecifications;
import ru.geekbrains.my.market.services.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam MultiValueMap<String, String> params, @RequestParam (name = "p", defaultValue = "1") int page){
        if (page < 1) {
            page = 1;
        }
        return productService.findPage(ProductSpecifications.build(params), page, 10);
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
