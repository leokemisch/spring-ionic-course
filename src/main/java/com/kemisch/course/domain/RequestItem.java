package com.kemisch.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "request_item")
public class RequestItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public RequestItem() {
    }

    public RequestItem(Request request, Product product, Double discount, Integer amount, Double price) {
        id.setRequest(request);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public Double getSubTotal() {
        return (price - discount) * amount;
    }

    @JsonIgnore
    public Request GetRequest() {
        return id.getRequest();
    }

    public void setRequest(Request request) {
        id.setRequest(request);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public RequestItemPK getId() {
        return id;
    }

    public void setId(RequestItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem that = (RequestItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
