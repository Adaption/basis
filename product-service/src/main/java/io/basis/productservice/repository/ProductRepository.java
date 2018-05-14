package io.basis.productservice.repository;

import io.basis.productservice.custom.repository.BasisCustomProductRepository;
import io.basis.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, BasisCustomProductRepository {
    List<Product> findByCategoryId(int id);
}
