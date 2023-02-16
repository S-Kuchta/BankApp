package com.StefanKuchta.BankApp.service.impl;

import com.StefanKuchta.BankApp.db.mapper.AccountRowMapper;
import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.service.api.AccountService;

import java.util.List;

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
    public Account getAccountById(int id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public Integer addUserAndReturnId(Account account) {
        return accountRepository.createAccount(account);
    }
}
