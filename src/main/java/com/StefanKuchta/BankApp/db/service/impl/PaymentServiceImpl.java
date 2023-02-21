package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.PaymentRepository;
import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;

import com.StefanKuchta.BankApp.db.service.functions.CheckIfIbanBelongToBank;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final Payment payment = new Payment();

    public PaymentServiceImpl(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }


    @Override
    public SendPaymentResponse sendPayment(Payment payment) {
        String payerIban = payment.getPayerIban();
        String receiverIban = payment.getReceiverIban();
        Long payerId = accountRepository.getIdFromIban(payerIban);
        Long receiverId = accountRepository.getIdFromIban(receiverIban);
        Double amount = payment.getAmount();
        double totalPayerBalance = accountRepository.getBalance(payerId) - amount;



        if(amount <= accountRepository.getBalance(payerId)) {
            if(!accountRepository.checkIfAccountExist(receiverIban)) {

            } else if(/*CheckIfIbanBelongToBank.ibanCheck(receiverIban)*/accountRepository.checkIfAccountExist(receiverIban)) {
                double totalReceiverBalance = accountRepository.getBalance(receiverId) + amount;
//                accountRepository.setBalance(payerId, totalPayerBalance);
//                paymentRepository.addPaymentToPaymentHistory(payment, payerId, "debt");
                accountRepository.setBalance(receiverId, totalReceiverBalance);
                paymentRepository.addPaymentToPaymentHistory(payment, receiverId, "credit");
            } /*else {*/
                accountRepository.setBalance(payerId, totalPayerBalance);
                paymentRepository.addPaymentToPaymentHistory(payment, payerId, "debt");
//            }

            return new SendPaymentResponse(true);
        } else {
            return new SendPaymentResponse(false, "Account with id: " + payerId + " don't have enough money.");
        }





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

    @Override
    public List<Payment> getAllPayments() {
//        payment.setPayerIban(BeautyIban.beautyIban(payment.getPayerIban()));
//        payment.setReceiverIban(BeautyIban.beautyIban(payment.getReceiverIban()));
        return paymentRepository.getAllPayments();
    }
}
