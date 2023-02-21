package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.CentralIbanDbRepository;
import com.StefanKuchta.BankApp.db.repository.PaymentRepository;
import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;

import com.StefanKuchta.BankApp.db.service.enums.TransactionType;
import com.StefanKuchta.BankApp.db.service.functions.CheckIfIbanBelongToBank;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final CentralIbanDbRepository centralIbanDbRepository;
    private final Payment payment = new Payment();

    public PaymentServiceImpl(AccountRepository accountRepository, PaymentRepository paymentRepository, CentralIbanDbRepository centralIbanDbRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.centralIbanDbRepository = centralIbanDbRepository;
    }


    @Override
    public SendPaymentResponse sendPayment(Payment payment) {
        String payerIban = payment.getPayerIban();
        String receiverIban = payment.getReceiverIban();
        Long payerId = accountRepository.getIdFromIban(payerIban);
        Double amount = payment.getAmount();
        double totalPayerBalance = accountRepository.getBalance(payerId) - amount;


        if(!centralIbanDbRepository.checkIfIbanExist(receiverIban)) {
            return new SendPaymentResponse(false,"Iban doesn't exist in central IBAN database");
        }

        if(amount <= accountRepository.getBalance(payerId)) {
            accountRepository.setBalance(payerId, totalPayerBalance);
            paymentRepository.addPaymentToPaymentHistory(payment, payerId, TransactionType.DEBT.getType());
            if(accountRepository.checkIfAccountExist(receiverIban)) {
                receivePayment(payment);
            }
            return new SendPaymentResponse(true);
        } else {
            return new SendPaymentResponse(false, "Account with id: " + payerId + " don't have enough money.");
        }
    }

    @Override
    public Long receivePayment(Payment payment) {
        Long receiverId = accountRepository.getIdFromIban(payment.getReceiverIban());
        double totalReceiverBalance = accountRepository.getBalance(receiverId) + payment.getAmount();

        if(CheckIfIbanBelongToBank.ibanCheck(payment.getReceiverIban())) {
            accountRepository.setBalance(receiverId, totalReceiverBalance);
            return paymentRepository.addPaymentToPaymentHistory(payment, receiverId, TransactionType.CREDIT.getType());
        } else {
            return null;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
//        payment.setPayerIban(BeautyIban.beautyIban(payment.getPayerIban()));
//        payment.setReceiverIban(BeautyIban.beautyIban(payment.getReceiverIban()));
        return paymentRepository.getAllPayments();
    }
}
