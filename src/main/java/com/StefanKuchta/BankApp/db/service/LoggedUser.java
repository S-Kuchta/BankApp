package com.StefanKuchta.BankApp.db.service;


import org.springframework.stereotype.Component;

@Component
public class LoggedUser {
    private Long id;

    public LoggedUser(Long id) {
        this.id = id;
    }

    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


