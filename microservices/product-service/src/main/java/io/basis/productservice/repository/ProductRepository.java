package io.basis.productservice.repository;

import io.basis.productservice.custom.repository.BasisCustomProductRepository;
import io.basis.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>, BasisCustomProductRepository {

}
