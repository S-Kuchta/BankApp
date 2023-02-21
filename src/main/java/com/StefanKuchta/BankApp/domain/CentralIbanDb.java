package com.StefanKuchta.BankApp.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class CentralIbanDb {

    @NonNull
    private Long id;
    @NonNull
    private String iban;

    public CentralIbanDb() {
    }

    public CentralIbanDb(@NonNull String iban) {
        this.iban = iban;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public String getIban() {
        return iban;
    }

    public void setIban(@NonNull String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentralIbanDb that = (CentralIbanDb) o;
        return id.equals(that.id) && iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban);
    }
}
