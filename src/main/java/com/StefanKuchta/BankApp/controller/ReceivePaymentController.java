package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receive-payment")
public class ReceivePaymentController {

    private final PaymentService paymentService;

    public ReceivePaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> receivePayment(@RequestBody Payment payment) {
        Long id = paymentService.receivePayment(payment);
        if(id == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }
}
