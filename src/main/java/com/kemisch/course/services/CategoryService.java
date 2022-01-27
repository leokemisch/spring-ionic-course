package com.kemisch.course.services;

import com.kemisch.course.domain.Category;
import com.kemisch.course.dto.CategoryDTO;
import com.kemisch.course.repositories.CategoryRepository;
import com.kemisch.course.services.exceptions.DataIntegrityException;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category insert(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {

        Category toUpdate = findById(category.getId());
        updateData(toUpdate, category);

        return repository.save(toUpdate);
    }

    public void delete(Integer id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to delete a category that has products.");
        }
    }

    public Category fromDTO(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName());
    }

    public void updateData(Category toUpdate, Category category) {
        toUpdate.setName(category.getName());
    }
}
