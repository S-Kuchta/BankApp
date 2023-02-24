package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.CentralIbanDbRepository;
import com.StefanKuchta.BankApp.db.repository.PaymentRepository;
import com.StefanKuchta.BankApp.db.service.LoggedUser;
import com.StefanKuchta.BankApp.db.service.api.PaymentService;
import com.StefanKuchta.BankApp.db.service.api.response.SendPaymentResponse;

import com.StefanKuchta.BankApp.db.service.enums.TransactionType;
import com.StefanKuchta.BankApp.db.service.functions.CheckIfIbanBelongsToBank;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {




    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final CentralIbanDbRepository centralIbanDbRepository;
    private final LoggedUser loggedUser;


    @Autowired
    public PaymentServiceImpl(AccountRepository accountRepository, PaymentRepository paymentRepository, CentralIbanDbRepository centralIbanDbRepository, LoggedUser loggedUser) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.centralIbanDbRepository = centralIbanDbRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public SendPaymentResponse sendPayment(Payment payment) {
        String receiverIban = payment.getReceiverIban();
        Long payerId = loggedUser.getId();
        payment.setPayerIban(accountRepository.getIbanFromId(payerId));
        Double amount = payment.getAmount();
        double totalPayerBalance = accountRepository.getBalance(payerId) - amount;

        if (!centralIbanDbRepository.checkIfIbanExist(receiverIban)) {
            return new SendPaymentResponse(false, "Iban does not exist in central IBAN database. Payment does not proceed.");
        }



        if (amount <= accountRepository.getBalance(payerId)) {
            accountRepository.setBalance(payerId, totalPayerBalance);
            paymentRepository.addPaymentToPaymentHistory(payment, payerId, TransactionType.DEBT.getType());
            if (accountRepository.checkIfAccountExist(receiverIban)) {
                receivePayment(payment);
            }
            return new SendPaymentResponse(true);
        } else {
            return new SendPaymentResponse(false, "The account with the id: " + payerId + " does not have enough money.");
        }
    }

    @Override
    public Long receivePayment(Payment payment) {
        Long receiverId = accountRepository.getIdFromIban(payment.getReceiverIban());
        double totalReceiverBalance = accountRepository.getBalance(receiverId) + payment.getAmount();

        if (CheckIfIbanBelongsToBank.ibanCheck(payment.getReceiverIban())) {
            accountRepository.setBalance(receiverId, totalReceiverBalance);
            return paymentRepository.addPaymentToPaymentHistory(payment, receiverId, TransactionType.CREDIT.getType());
        } else {
            return null;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public List<Payment> getPaymentsByAccountIdAndTypeOfTransaction(long id, String type) {
        return paymentRepository.getPaymentsByAccountIdAndTypeOfTransaction(id, type);
    }
}
