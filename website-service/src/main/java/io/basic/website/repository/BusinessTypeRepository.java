package io.basic.website.repository;

import io.basic.website.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
    List<BusinessType> findAll();


}
