package com.kemisch.course.services;

import com.kemisch.course.domain.Category;
import com.kemisch.course.domain.Product;
import com.kemisch.course.repositories.CategoryRepository;
import com.kemisch.course.repositories.ProductRepository;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        Optional<Product> Request = repository.findById(id);

        return Request.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);

        return repository.search(name, categories, pageRequest);
    }

}
