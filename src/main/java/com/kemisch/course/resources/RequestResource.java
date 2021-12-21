package com.kemisch.course.resources;

import com.kemisch.course.domain.Request;
import com.kemisch.course.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requests")
public class RequestResource {

    @Autowired
    RequestService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Request> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

}
