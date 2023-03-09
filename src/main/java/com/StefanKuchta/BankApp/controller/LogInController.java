package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.db.service.LoggedUser;
import com.StefanKuchta.BankApp.db.service.api.LogInService;

import com.StefanKuchta.BankApp.db.service.api.response.LogInUserResponse;
import com.StefanKuchta.BankApp.domain.LogInData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("log-in")
public class LogInController {

    private final LogInService logInService;
    private final LoggedUser loggedUser;

    public LogInController(LogInService logInService, LoggedUser loggedUser) {
        this.logInService = logInService;
        this.loggedUser = loggedUser;
    }

    @PostMapping
    public ResponseEntity<String> logInUser(@RequestBody LogInData logInData) {
        LogInUserResponse logInUserResponse = logInService.logInRequest(logInData);
        if(logInUserResponse.isSuccessful()){
            return new ResponseEntity<>("Successfully log in", HttpStatus.SEE_OTHER);
        } else {
            return new ResponseEntity<>(logInUserResponse.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<Long> getLoggedInUser() {
        Long id = loggedUser.getId();
        if(id == null) {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

}
