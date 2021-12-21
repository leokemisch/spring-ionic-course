package com.kemisch.course.resources;

import com.kemisch.course.domain.Client;
import com.kemisch.course.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    ClientService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
