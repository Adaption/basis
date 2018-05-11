package io.basic.website.repository;

import io.basic.website.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteRepository extends JpaRepository<Website, Integer> {
    List<Website> findAll();
}
