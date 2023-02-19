package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.PaymentRepository;
import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;

import com.StefanKuchta.BankApp.db.service.functions.CheckIfIbanBelongToBank;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }


    @Override
    public SendPaymentResponse sendPayment() {
        return null;
    }

    @Override
    public Long receivePayment(Payment payment) {
        Long accountId = accountRepository.getIdFromIban(payment.getReceiverIban());
        double totalBalance = accountRepository.getBalance(accountId) + payment.getAmount();

        if(CheckIfIbanBelongToBank.ibanCheck(payment.getReceiverIban())) {
            accountRepository.setBalance(accountId, totalBalance);
            return paymentRepository.addPaymentToPaymentHistory(payment, accountId);
        } else {
            return null;
        }
    }
}
