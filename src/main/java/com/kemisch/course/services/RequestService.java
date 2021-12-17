package com.kemisch.course.services;

import com.kemisch.course.domain.Request;
import com.kemisch.course.repositories.RequestRepository;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository repository;

    public Request findById(Integer id) {
        Optional<Request> Request = repository.findById(id);

        return Request.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Request.class.getName()));
    }

}
