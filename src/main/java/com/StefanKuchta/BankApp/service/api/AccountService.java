package com.StefanKuchta.BankApp.service.api;

import com.StefanKuchta.BankApp.domain.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccountById(int id);

    Integer addUserAndReturnId(Account account);

}
