package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.UserRepository;
import com.StefanKuchta.BankApp.db.service.LoggedUser;
import com.StefanKuchta.BankApp.db.service.api.LogInService;
import com.StefanKuchta.BankApp.db.service.api.response.LogInUserResponse;
import com.StefanKuchta.BankApp.domain.LogInData;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {


    private final UserRepository userRepository;
    private final LoggedUser loggedUser;


    public LogInServiceImpl(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public LogInUserResponse logInRequest(LogInData logInData) {
        Long id = userRepository.checkEmailAndPassword(logInData.email(),logInData.password());
        if(id == null) {
            loggedUser.setId(null);
            return new LogInUserResponse(false, "Entered bad email or password.");
        } else {
            loggedUser.setId(id);
            return new LogInUserResponse(true);
        }
    }

}
