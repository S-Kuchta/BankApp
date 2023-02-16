package com.StefanKuchta.BankApp.domain;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class User {

    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private String telNumber;

    public User() {}

    public User(@NonNull String name, @NonNull String surname, @NonNull String email, @NonNull String telNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telNumber = telNumber;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(@NonNull String telNumber) {
        this.telNumber = telNumber;
    }


}
