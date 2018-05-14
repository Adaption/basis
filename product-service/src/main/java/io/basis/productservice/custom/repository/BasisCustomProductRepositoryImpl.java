package io.basis.productservice.custom.repository;

import io.basis.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
public class BasisCustomProductRepositoryImpl implements BasisCustomProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private String findJsonAttributeQuery = "SELECT * FROM product WHERE attributes->'$.\"Nhà sản xuất\"' LIKE ?1";

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findByJsonAttribute(String attribute, String value) {
        JsonAttributeQueryBuilder queryBuilder = new JsonAttributeQueryBuilder();

        String attributeQuery = queryBuilder
                .setAttribute(attribute)
                .build();

        log.info(attributeQuery);

        Query query = entityManager.createNativeQuery(attributeQuery, Product.class);
        query.setParameter("value", "%" + value + "%");

        return (List<Product>) query.getResultList();
    }
}

class JsonAttributeQueryBuilder {

    private String attribute;

    JsonAttributeQueryBuilder setAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    String build() {
        String query = "SELECT * FROM product WHERE attributes->";
        return query + "'$.\"" + attribute + "\"' LIKE :value";
    }
}