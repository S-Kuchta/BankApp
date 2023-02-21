package com.StefanKuchta.BankApp.db.service.enums;

public enum TransactionType {
    CREDIT("credit"),
    DEBT("debt");

    private final String type;

    private TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
