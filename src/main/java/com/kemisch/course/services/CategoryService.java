package com.kemisch.course.services;

import com.kemisch.course.domain.Category;
import com.kemisch.course.repositories.CategoryRepository;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category findById(Integer id) {
        Optional<Category> category = repository.findById(id);

        return category.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Category.class.getName()));
    }

    public Category insert(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

}
