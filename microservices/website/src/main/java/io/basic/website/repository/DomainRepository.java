package io.basic.website.repository;

import io.basic.website.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    List<Domain> findAll();
}
