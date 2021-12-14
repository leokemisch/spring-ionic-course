package com.kemisch.course.service;

import com.kemisch.course.domain.Category;
import com.kemisch.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public Category findById(Integer id) {
        Optional<Category> category = repository.findById(id);

        return category.orElse(null);
    }

}
