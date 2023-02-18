package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.db.service.api.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    public Long addUserAndReturnId(Account account) {
        return accountRepository.createAccount(account);
    }
}