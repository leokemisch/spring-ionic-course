package com.kemisch.course.services;

import com.kemisch.course.domain.Category;
import com.kemisch.course.repositories.CategoryRepository;
import com.kemisch.course.services.exceptions.DataIntegrityException;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category findById(Integer id) {
        Optional<Category> category = repository.findById(id);

        return category.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Category.class.getName()));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category insert(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

    public void delete(Integer id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to delete a category that has products.");
        }
    }
}
