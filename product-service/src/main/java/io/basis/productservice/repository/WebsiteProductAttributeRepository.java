package io.basis.productservice.repository;

import io.basis.productservice.model.WebsiteProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteProductAttributeRepository extends JpaRepository<WebsiteProductAttribute, Integer> {

}
