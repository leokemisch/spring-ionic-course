package com.kemisch.course.services;

import com.kemisch.course.domain.Client;
import com.kemisch.course.domain.Client;
import com.kemisch.course.dto.ClientDTO;
import com.kemisch.course.repositories.ClientRepository;
import com.kemisch.course.services.exceptions.DataIntegrityException;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Client findById(Integer id) {
        Optional<Client> Client = repository.findById(id);

        return Client.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Client.class.getName()));
    }
    public List<Client> findAll() {
        return repository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    @Transactional
    public Client insert(Client client) {
        return repository.save(client);
    }

    public Client update(Client client) {

        Client toUpdate = findById(client.getId());
        updateData(toUpdate, client);

        return repository.save(toUpdate);
    }

    public void delete(Integer id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to delete because there's related entity.");
        }
    }

    public Client fromDTO(ClientDTO dto){
        return new Client(dto.getId(), dto.getName(), dto.getMail(), null,null);
    }

    private void updateData(Client toUpdate, Client client){
        toUpdate.setName(client.getName());
        toUpdate.setMail(client.getMail());
    }

}
