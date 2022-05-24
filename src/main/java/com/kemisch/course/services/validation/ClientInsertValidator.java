package com.kemisch.course.services.validation;

import com.kemisch.course.domain.enums.ClientType;
import com.kemisch.course.dto.NewClientDTO;
import com.kemisch.course.resources.exceptions.FieldMessage;
import com.kemisch.course.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(ClientType.PHYSICAL_STAFF.getId()) && !BR.isValidCPF(objDto.getDocument())) {
            list.add(new FieldMessage("Document", "CPF Is Not Valid"));
        }

        if(objDto.getType().equals(ClientType.LEGAL_PERSON.getId()) && !BR.isValidCNPJ(objDto.getDocument())) {
            list.add(new FieldMessage("Document", "CNPJ Is Not Valid"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
