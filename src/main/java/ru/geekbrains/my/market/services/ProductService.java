package ru.geekbrains.my.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

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
            if (product.getPrice()!=0){
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
