package com.kemisch.course.resources;

import com.kemisch.course.domain.Product;
import com.kemisch.course.dto.CategoryDTO;
import com.kemisch.course.dto.ProductDTO;
import com.kemisch.course.resources.utils.URL;
import com.kemisch.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        List<Integer> ids = URL.decodeIntList(categories);
        String decodedName = URL.decodeParam(name);

        Page<ProductDTO> list = service.search(decodedName, ids, page, linesPerPage, orderBy, direction).map(
                product -> new ProductDTO(product)
        );

        return ResponseEntity.ok().body(list);
    }

}
