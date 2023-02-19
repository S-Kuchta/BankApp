package com.StefanKuchta.BankApp.db.service.api;

import com.StefanKuchta.BankApp.db.service.api.request.SendPaymentRequest;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;
import com.StefanKuchta.BankApp.domain.Payment;

public interface PaymentService {

    SendPaymentResponse sendPayment();

    Long receivePayment(Payment payment);
}
