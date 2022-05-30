package com.kemisch.course.services;

import com.kemisch.course.domain.BankSlipPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankSlipService {

    public void fillPayment(BankSlipPayment payment, Date instant) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());

    }

}
