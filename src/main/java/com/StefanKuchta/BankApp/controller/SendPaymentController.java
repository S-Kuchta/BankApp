package com.StefanKuchta.BankApp.controller;


import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send_payment")
public class SendPaymentController {

    private final Payment payment;
    private final PaymentService paymentService;

}
