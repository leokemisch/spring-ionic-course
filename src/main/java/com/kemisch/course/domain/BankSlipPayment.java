package com.kemisch.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemisch.course.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankSlipPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payday;

    public BankSlipPayment() {

    }

    public BankSlipPayment(Integer id, PaymentState state, Request request, Date dueDate, Date payday) {
        super(id, state, request);
        this.dueDate = dueDate;
        this.payday = payday;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }


}
