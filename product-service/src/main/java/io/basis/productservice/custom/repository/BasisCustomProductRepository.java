package io.basis.productservice.custom.repository;

import io.basis.productservice.model.Product;

import java.util.List;

public interface BasisCustomProductRepository {

    public List<Product> findByJsonAttribute(String attribute, String value);
}
