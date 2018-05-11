package io.basic.website.repository;

import io.basic.website.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteRepository extends JpaRepository<Website, Integer> {
    List<Website> findAll();

    List<Website> findAllByStatus(boolean status);

    Optional<Website> findByIdAndStatus(int id, boolean status);
}
