package com.kemisch.course.services.validation;

import com.kemisch.course.domain.Client;
import com.kemisch.course.dto.ClientDTO;
import com.kemisch.course.repositories.ClientRepository;
import com.kemisch.course.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        Client aux = clientRepository.findByMail(objDto.getMail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("Mail", "Mail already registered"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
