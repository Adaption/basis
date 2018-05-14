package io.basis.productservice.service;

import io.basis.productservice.custom.exception.ProductNotFoundException;
import io.basis.productservice.model.Product;
import io.basis.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByAttribute(String attribute, String value) {
        return productRepository.findByJsonAttribute(attribute, value);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        productRepository.save(product);
        productRepository.flush();
        return product;
    }

    public List<Product> findByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public Product update(int productId, Product modifiedProduct)
            throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setName(modifiedProduct.getName());

            product.setPrice(modifiedProduct.getPrice());
            product.setDiscount(modifiedProduct.getDiscount());

            product.setStatus(modifiedProduct.getStatus());

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }

    public Product updateAttribute(int productId, String attributes) throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setAttributes(attributes);

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }

    public Product updateImages(int productId, String images) throws ProductNotFoundException {
        return productRepository.findById(productId).map(product -> {
            product.setImages(images);

            product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            productRepository.save(product);
            productRepository.flush();

            return product;
        }).orElseThrow(ProductNotFoundException::new);
    }
}
