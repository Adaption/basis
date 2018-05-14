package io.basic.website.repository;

import io.basic.website.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {
    List<Page> findAll();
}
