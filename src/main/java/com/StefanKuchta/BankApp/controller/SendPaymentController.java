package com.StefanKuchta.BankApp.controller;


import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send-payment")
public class SendPaymentController {

    private final PaymentService paymentService;

    public SendPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity SendPaymentController(@RequestBody Payment payment) {
        SendPaymentResponse sendPaymentResponse = paymentService.sendPayment(payment);
        if(sendPaymentResponse.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(sendPaymentResponse.getErrorMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
