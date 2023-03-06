package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.db.service.LoggedUser;
import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final LoggedUser loggedUser;

    public PaymentController(PaymentService paymentService, LoggedUser loggedUser) {
        this.paymentService = paymentService;
        this.loggedUser = loggedUser;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> paymentList = paymentService.getAllPayments();
        return new ResponseEntity<>(paymentList, HttpStatus.OK);
    }

    @GetMapping(/*"{id}/*/"{type}")
    public ResponseEntity getPaymentsByAccountIdAndTypeOfTransaction(/*@PathVariable ("id") long id, */@PathVariable String type) {
//        List<Payment> paymentList = paymentService.getPaymentsByAccountIdAndTypeOfTransaction(id, type);
        List<Payment> paymentList = paymentService.getPaymentsByAccountIdAndTypeOfTransaction(loggedUser.getId(), type);
        if(loggedUser.getId() == null) {
            return new ResponseEntity<>("You are not logged in", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(paymentList, HttpStatus.OK);
        }
    }
}
