package com.StefanKuchta.BankApp.service.api;

import com.StefanKuchta.BankApp.domain.Account;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    @Nullable
    Account getAccountById(int id);

    @Nullable
    Integer addUserAndReturnId(Account account);

}
