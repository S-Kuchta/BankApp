package com.StefanKuchta.BankApp.db.service.api;

import com.StefanKuchta.BankApp.db.service.api.request.SendPaymentRequest;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;

public interface PaymentService {

    SendPaymentResponse sendPayment();
}