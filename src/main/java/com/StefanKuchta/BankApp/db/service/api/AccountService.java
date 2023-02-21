package com.StefanKuchta.BankApp.db.service.api;

import com.StefanKuchta.BankApp.domain.Account;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    @Nullable
    Account getAccountById(long id);

    @Nullable
    Long addAccountAndReturnId(Account account);

}
