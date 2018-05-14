package io.basis.productservice.controller;

import io.basis.productservice.custom.exception.CategoryNotFoundException;
import io.basis.productservice.model.Category;
import io.basis.productservice.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return !categories.isEmpty() ? status(OK).body(categories) : status(NO_CONTENT).build();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") int categoryId) {
        return categoryService.findById(categoryId)
                .map(category -> status(OK).body(category))
                .orElseGet(status(NOT_FOUND)::build);
    }

    @PutMapping(value = "/categories/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> update(@PathVariable("id") int categoryId,
                                           @RequestBody Category category) {
        try {
            return ResponseEntity.status(OK).body(categoryService.update(categoryId, category));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return status(CREATED).body(categoryService.create(category));
    }
}
