package com.kemisch.course.dto;

import com.kemisch.course.domain.Client;
import com.kemisch.course.services.validation.ClientUpdate;

import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String mail;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        mail = client.getMail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
