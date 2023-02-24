package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.service.LoggedUser;
import com.StefanKuchta.BankApp.db.service.api.LogOutService;
import org.springframework.stereotype.Service;

@Service
public class LogOutServiceImpl implements LogOutService {


    private final LoggedUser loggedUser;

    public LogOutServiceImpl(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void logOut() {
        loggedUser.setId(null);
    }
}
