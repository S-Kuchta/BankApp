package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.db.service.api.LogOutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log-out")
public class LogOutController {

    private final LogOutService logOutService;

    public LogOutController(LogOutService logOutService) {
        this.logOutService = logOutService;
    }

    @PostMapping
    public ResponseEntity<String> logOut() {
        logOutService.logOut();
        return new ResponseEntity<>("You have been logged out.", HttpStatus.OK);
    }
}
