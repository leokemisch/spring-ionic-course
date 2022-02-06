package com.kemisch.course.services;

import com.kemisch.course.domain.Address;
import com.kemisch.course.domain.City;
import com.kemisch.course.domain.Client;
import com.kemisch.course.domain.enums.ClientType;
import com.kemisch.course.dto.ClientDTO;
import com.kemisch.course.dto.NewClientDTO;
import com.kemisch.course.repositories.AddressRepository;
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

    @Autowired
    AddressRepository addressRepository;

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

        client.getAddresses().forEach(a -> addressRepository.save(a));

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

    public Client fromDTO(ClientDTO dto) {
        return new Client(dto.getId(), dto.getName(), dto.getMail(), null, null);
    }

    public Client fromDTO(NewClientDTO dto) {

        Client client = new Client(null, dto.getName(), dto.getMail(), dto.getDocument(), ClientType.toEnum(dto.getType()));
        City city = new City(dto.getCityId(), null, null);
        Address address = new Address(null, dto.getStreet(), dto.getNumber(), dto.getComplement(), dto.getDistrict(), dto.getZipcode(), client, city);
        client.getAddresses().add(address);
        client.getPhones().add(dto.getFone1());

        if (dto.getFone2() != null) client.getPhones().add(dto.getFone2());
        if (dto.getFone3() != null) client.getPhones().add(dto.getFone3());

        return client;
    }


    private void updateData(Client toUpdate, Client client) {
        toUpdate.setName(client.getName());
        toUpdate.setMail(client.getMail());
    }
}
