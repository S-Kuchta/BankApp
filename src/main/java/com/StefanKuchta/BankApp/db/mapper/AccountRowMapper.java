package com.StefanKuchta.BankApp.db.mapper;

import com.StefanKuchta.BankApp.domain.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setUserId(rs.getInt("user_id"));
        account.setIban(rs.getString("iban"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    }
}
