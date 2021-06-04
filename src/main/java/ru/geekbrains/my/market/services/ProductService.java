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
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Category;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Transactional
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("Category doesn't exist " + productDto.getCategoryTitle()));
        product.setCategory(category);
        productRepository.save(product);
        return new ProductDto(product);
    }

    public Page<ProductDto> findPage(Specification<Product> spec, int page, int pageSize){

        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);

        //return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        if(productRepository.findById(product.getId()).isPresent()){
            Product p = productRepository.findById(product.getId()).get();
            if(product.getTitle()!=null){
                p.setTitle(product.getTitle());
            }
            if (product.getPrice()!=null){
                p.setPrice(product.getPrice());
            }
            return  productRepository.save(p);
        }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public Product deleteById(Long id){
        if(productRepository.findById(id).isPresent()) {
            Product product = productRepository.findById(id).get();
            productRepository.delete(product);
            return product;
        } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
    }

}
