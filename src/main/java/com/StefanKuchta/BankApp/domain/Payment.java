package com.StefanKuchta.BankApp.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.Objects;

public class Payment {

    @Nullable
    private Long id;
    @Nullable
    private Long accountId;
    @NonNull
    private String payerIban;
    @NonNull
    private String receiverIban;
    @NonNull
    private Double amount;
    @Nullable
    private String information;
    @Nullable
    private String variableNumber;
    @NonNull
    private Timestamp payedAt;
    @NonNull
    private String type;


    public Payment() {
    }

    public Payment(/*@Nullable Long accountId,*/ @NonNull String payerIban, @NonNull String receiverIban, @NonNull Double amount, @Nullable String information, @Nullable String variableNumber, @NonNull Timestamp payedAt, @NonNull String type) {
//        this.accountId = accountId;
        this.payerIban = payerIban;
        this.receiverIban = receiverIban;
        this.amount = amount;
        this.information = information;
        this.variableNumber = variableNumber;
        this.payedAt = payedAt;
        this.type = type;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @Nullable
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@Nullable Long accountId) {
        this.accountId = accountId;
    }

    @NonNull
    public String getPayerIban() {
        return payerIban;
    }

    public void setPayerIban(@NonNull String payerIban) {
        this.payerIban = payerIban;
    }

    @NonNull
    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(@NonNull String receiverIban) {
        this.receiverIban = receiverIban;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Double amount) {
        this.amount = amount;
    }

    @Nullable
    public String getInformation() {
        return information;
    }

    public void setInformation(@Nullable String information) {
        this.information = information;
    }

    @Nullable
    public String getVariableNumber() {
        return variableNumber;
    }

    public void setVariableNumber(@Nullable String variableNumber) {
        this.variableNumber = variableNumber;
    }

    @NonNull
    public Timestamp getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(@NonNull Timestamp payedAt) {
        this.payedAt = payedAt;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id)
                && Objects.equals(accountId, payment.accountId)
                && payerIban.equals(payment.payerIban)
                && receiverIban.equals(payment.receiverIban)
                && amount.equals(payment.amount)
                && Objects.equals(information, payment.information)
                && Objects.equals(variableNumber, payment.variableNumber)
                && payedAt.equals(payment.payedAt)
                && type.equals(payment.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, payerIban, receiverIban, amount, information, variableNumber, payedAt, type);
    }
}
