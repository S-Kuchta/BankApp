package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.CentralIbanDbRepository;
import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.db.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CentralIbanDbRepository centralIbanDbRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CentralIbanDbRepository centralIbanDbRepository) {
        this.accountRepository = accountRepository;
        this.centralIbanDbRepository = centralIbanDbRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public Account getAccountById(long id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public Long addAccountAndReturnId(Account account) {
        Long id = accountRepository.createAccountAndReturnGeneratedId(account);
        centralIbanDbRepository.addIbanToCentralIbanDb(accountRepository.getIbanFromId(id));
        return id;
    }
}
