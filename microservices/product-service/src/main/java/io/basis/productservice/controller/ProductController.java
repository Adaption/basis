package io.basis.productservice.controller;

import io.basis.productservice.model.Product;
import io.basis.productservice.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> findByAttribute(@RequestParam("attribute") String attribute,
                                         @RequestParam("value") String value) {
//  examples:
//      http://localhost:8080/products?attribute=Đèn LED&value=RGB
//      http://localhost:8080/products?attribute=Nhà sản xuất&value=Razer
        return productRepository.findByJsonAttribute(attribute, value);
    }
}
