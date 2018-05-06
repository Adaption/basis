package io.basis.jsonqueryhandler.controller;

import io.basis.jsonqueryhandler.model.Product;
import io.basis.jsonqueryhandler.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Product> getByProducer(@RequestParam("producer") String producer) {
        return productRepository.findByAttributesProducer("%" + producer + "%");
    }
}
