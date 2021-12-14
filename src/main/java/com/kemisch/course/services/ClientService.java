package com.kemisch.course.services;

import com.kemisch.course.domain.Client;
import com.kemisch.course.repositories.ClientRepository;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Client findById(Integer id) {
        Optional<Client> Client = repository.findById(id);

        return Client.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Client.class.getName()));
    }

}
