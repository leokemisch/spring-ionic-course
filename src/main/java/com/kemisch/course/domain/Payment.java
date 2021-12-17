package com.kemisch.course.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kemisch.course.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.JOINED) //JOINED generate one table, SINGLE_TABLE one for each subclass
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer state;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "request_id")
    @MapsId //Makes the payment id equals request_id
    private Request request;

    public Payment() {
    }

    public Payment(Integer id, PaymentState state, Request request) {
        this.id = id;
        this.state = state.getId();
        this.request = request;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getState() {
        return PaymentState.toEnum(state);
    }

    public void setState(PaymentState state) {
        this.state = state.getId();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
