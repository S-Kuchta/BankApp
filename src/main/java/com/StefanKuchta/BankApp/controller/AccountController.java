package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.db.service.api.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody Account account) {
        Long id = accountService.addAccountAndReturnId(account);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
        Account account = accountService.getAccountById(id);
        if(account == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }
}
