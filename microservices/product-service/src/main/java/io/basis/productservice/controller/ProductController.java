package io.basis.productservice.controller;

import io.basis.productservice.custom.exception.ProductNotFoundException;
import io.basis.productservice.model.Product;
import io.basis.productservice.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products?attribute={attribute}&value={value}")
    public ResponseEntity<List<Product>> findByAttribute(@PathVariable("attribute") String attribute,
                                                         @PathVariable("value") String value) {
//  examples:
//      http://localhost:8080/products?attribute=Đèn LED&value=RGB
//      http://localhost:8080/products?attribute=Nhà sản xuất&value=Razer

        List<Product> products = productService.findByAttribute(attribute, value);
        return !products.isEmpty() ? status(OK).body(products) : status(NO_CONTENT).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return !products.isEmpty() ? status(OK).body(products) : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return status(CREATED).body(productService.create(product));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") int productId) {
        return productService.findById(productId)
                .map(product -> status(OK).body(product))
                .orElseGet(status(NOT_FOUND)::build);
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable("id") int categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        return !products.isEmpty() ? status(OK).body(products) : status(NO_CONTENT).build();
    }

    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> update(@PathVariable("id") int productId,
                                          @RequestBody Product modifiedProduct) {
        try {
            return ResponseEntity.status(OK).body(productService.update(productId, modifiedProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @PutMapping("/products/{id}/attributes")
    public ResponseEntity<Product> updateAttributes(@PathVariable("id") int productId, String attributes) {
        try {
            return ResponseEntity.status(OK).body(productService.updateAttribute(productId, attributes));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @PutMapping("/products/{id}/images")
    public ResponseEntity<Product> updateImages(@PathVariable("id") int productId, String images) {
        try {
            return ResponseEntity.status(OK).body(productService.updateImages(productId, images));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }
}
