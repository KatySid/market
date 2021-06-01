package ru.geekbrains.my.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.my.market.services.ProductService;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.soap.products.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/my/market/products";
    private final ProductService productService;

    public static final Function<Product, ru.geekbrains.my.market.soap.products.Product>
            functionProductToSoap = product -> {
        ru.geekbrains.my.market.soap.products.Product ps = new ru.geekbrains.my.market.soap.products.Product();
        ps.setId(product.getId());
        ps.setName(product.getTitle());
        ps.setPrice(product.getPrice());
        ps.setCategoryTitle(product.getCategory().getTitle());
        return ps;
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        Long id = (Long) request.getId();
        ru.geekbrains.my.market.soap.products.Product productSoap =
                productService.findById(id).map(functionProductToSoap).get();
        response.setProduct(productSoap);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ru.geekbrains.my.market.soap.products.Product> productsSoap =
                productService.findAll().stream().map(functionProductToSoap).collect(Collectors.toList());
        productsSoap.forEach(response.getProducts()::add);

        return response;
    }
}
