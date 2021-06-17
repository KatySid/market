package ru.geekbrains.my.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.my.market.models.Category;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.repositories.ProductRepository;
import ru.geekbrains.my.market.services.CategoryService;
import ru.geekbrains.my.market.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    public ProductService productService;

    @MockBean
    public ProductRepository productRepository;

    @Test
    public void findOneProductByTest(){
        Product product = new Product();
        product.setTitle("Book1");
        product.setPrice(BigDecimal.valueOf(100));
        product.setCategory(new Category("Books"));

        Mockito.doReturn(Optional.of(product)).when(productRepository).findOneByTitle("Book1");

        Product book = productRepository.findOneByTitle("Book1").get();
        Assertions.assertNotNull(book);
        Assertions.assertEquals(product.getPrice(), book.getPrice());
        Assertions.assertEquals("Books", book.getCategory().getTitle());

    }
}
