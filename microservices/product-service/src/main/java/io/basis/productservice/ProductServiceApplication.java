package io.basis.productservice;

import io.basis.productservice.model.Product;
import io.basis.productservice.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}

@RestController
class ProductController {

    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/producer={producer}")
    public List<Product> getByProducer(@PathVariable("producer") String producer) {
        return productRepository.findByAttributesProducer("%" + producer + "%");
    }

    @GetMapping("/products/producer")
    public List getProducer() {
        return productRepository.findTestJson();
    }
}
