package com.StefanKuchta.BankApp.controller;


import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send-payment")
public class SendPaymentController {

    private final PaymentService paymentService;

    public SendPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
