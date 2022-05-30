package com.kemisch.course.services;

import com.kemisch.course.domain.BankSlipPayment;
import com.kemisch.course.domain.Request;
import com.kemisch.course.domain.RequestItem;
import com.kemisch.course.domain.enums.PaymentState;
import com.kemisch.course.repositories.PaymentRepository;
import com.kemisch.course.repositories.ProductRepository;
import com.kemisch.course.repositories.RequestItemRepository;
import com.kemisch.course.repositories.RequestRepository;
import com.kemisch.course.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository repository;

    @Autowired
    BankSlipService bankSlipService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ProductService productService;

    @Autowired
    RequestItemRepository requestItemRepository;


    public Request findById(Integer id) {
        Optional<Request> Request = repository.findById(id);

        return Request.orElseThrow(() -> new NotFoundException("Object not found! Id: " + id + ", type: " + Request.class.getName()));
    }

    public Request insert(Request request) {

        request.setInstant(new Date());
        request.getPayment().setState(PaymentState.PENDING);
        request.getPayment().setRequest(request);

        if (request.getPayment() instanceof BankSlipPayment) {
            BankSlipPayment payment = (BankSlipPayment) request.getPayment();
            bankSlipService.fillPayment(payment, request.getInstant());

        }

        request = repository.save(request);
        paymentRepository.save(request.getPayment());

        for (RequestItem ri : request.getItems()) {
            ri.setDiscount(0.0);
            ri.setProduct(productService.findById(ri.getProduct().getId()));
            ri.setPrice(ri.getProduct().getPrice());
            ri.setRequest(request);
        }

        requestItemRepository.saveAll(request.getItems());

        return request;
    }

}
