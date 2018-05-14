package io.basis.productservice.service;

import io.basis.productservice.custom.exception.CategoryNotFoundException;
import io.basis.productservice.model.Category;
import io.basis.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        categoryRepository.save(category);
        categoryRepository.flush();
        return category;
    }

    public Optional<Category> findById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category update(int categoryId, Category modifiedCategory) throws CategoryNotFoundException {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setName(modifiedCategory.getName());
            category.setDescription(modifiedCategory.getDescription());

            categoryRepository.save(category);
            categoryRepository.flush();

            return category;
        }).orElseThrow(CategoryNotFoundException::new);
    }
}
