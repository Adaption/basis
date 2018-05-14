package io.basic.website.repository;

import io.basic.website.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    List<Domain> findAll();

    List<Domain> findAllByStatus(boolean status);

    Optional<Domain> findByIdAndStatus(int id, boolean status);
}
