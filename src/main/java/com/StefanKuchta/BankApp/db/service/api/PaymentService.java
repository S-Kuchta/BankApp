package com.StefanKuchta.BankApp.db.service.api;

import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;
import com.StefanKuchta.BankApp.domain.Payment;

import java.util.List;

public interface PaymentService {

    SendPaymentResponse sendPayment(Payment payment);

    Long receivePayment(Payment payment);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByAccountIdAndTypeOfTransaction(long id, String type);
}
