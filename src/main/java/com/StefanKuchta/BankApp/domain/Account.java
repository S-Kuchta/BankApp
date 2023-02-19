package com.StefanKuchta.BankApp.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class Account {

    @Nullable
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String iban;
    @NonNull
    private String name;
    @NonNull
    private Double balance;

    public Account() {
    }

    public Account(@NonNull Long userId, @NonNull String iban, @NonNull String name, @NonNull Double balance) {
        this.userId = userId;
        this.iban = iban;
        this.name = name;
        this.balance = balance;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    @NonNull
    public String getIban() {
        return iban;
    }

    public void setIban(@NonNull String iban) {
        this.iban = iban;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Double getBalance() {
        return balance;
    }

    public void setBalance(@NonNull Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && userId.equals(account.userId) && iban.equals(account.iban) && name.equals(account.name) && balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, iban, name, balance);
    }
}
