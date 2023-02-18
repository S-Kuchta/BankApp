package com.StefanKuchta.BankApp.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.Objects;

public class Payment {

    @Nullable
    private Long id;

    @NonNull
    private Long accountId;
    @NonNull
    private String iban;
    @NonNull
    private Double amount;
    @NonNull
    private String information;
    @NonNull
    private Timestamp payedAt;
    @Nullable
    private Integer variableNumber;

    public Payment() {
    }

    public Payment(@NonNull Long accountId, @NonNull String iban, @NonNull Double amount, @Nullable String information, @NonNull Timestamp payedAt, @Nullable Integer variableNumber) {
        this.accountId = accountId;
        this.iban = iban;
        this.amount = amount;
        this.information = information;
        this.payedAt = payedAt;
        this.variableNumber = variableNumber;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@NonNull Long accountId) {
        this.accountId = accountId;
    }

    @NonNull
    public String getIban() {
        return iban;
    }

    public void setIban(@NonNull String iban) {
        this.iban = iban;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Double amount) {
        this.amount = amount;
    }

    @NonNull
    public String getInformation() {
        return information;
    }

    public void setInformation(@NonNull String information) {
        this.information = information;
    }

    @NonNull
    public Timestamp getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(@NonNull Timestamp payedAt) {
        this.payedAt = payedAt;
    }

    @Nullable
    public Integer getVariableNumber() {
        return variableNumber;
    }

    public void setVariableNumber(@Nullable Integer variableNumber) {
        this.variableNumber = variableNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id)
                && accountId.equals(payment.accountId)
                && iban.equals(payment.iban)
                && amount.equals(payment.amount)
                && information.equals(payment.information)
                && payedAt.equals(payment.payedAt)
                && Objects.equals(variableNumber, payment.variableNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, iban, amount, information, payedAt, variableNumber);
    }
}
