package com.kemisch.course.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.kemisch.course.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("CardPayment")
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer installments;

    public CardPayment() {

    }

    public CardPayment(Integer id, PaymentState state, Request request, Integer installments) {
        super(id, state, request);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
