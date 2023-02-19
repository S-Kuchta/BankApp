package com.StefanKuchta.BankApp.db.service.api.request;

import java.util.Objects;

public class SendPaymentRequest {

    private Long accountId;
    private String receiverIban;
    private Double amount;
    private Integer variableNumber;
    private String information;


    public SendPaymentRequest(Long accountId, String receiverIban, Double amount, Integer variableNumber, String information) {
        this.accountId = accountId;
        this.receiverIban = receiverIban;
        this.amount = amount;
        this.variableNumber = variableNumber;
        this.information = information;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiverIban) {
        this.receiverIban = receiverIban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getVariableNumber() {
        return variableNumber;
    }

    public void setVariableNumber(Integer variableNumber) {
        this.variableNumber = variableNumber;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPaymentRequest that = (SendPaymentRequest) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(receiverIban, that.receiverIban) && Objects.equals(amount, that.amount) && Objects.equals(variableNumber, that.variableNumber) && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, receiverIban, amount, variableNumber, information);
    }
}
