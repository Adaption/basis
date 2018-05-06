package io.basis.jsonqueryhandler.repository;

import io.basis.jsonqueryhandler.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product WHERE attributes->'$.\"Nhà sản xuất\"' LIKE ?1", nativeQuery = true)
    List<Product> findByAttributesProducer(String producer);
}
