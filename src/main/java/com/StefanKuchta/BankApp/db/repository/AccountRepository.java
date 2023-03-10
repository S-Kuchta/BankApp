package com.StefanKuchta.BankApp.db.repository;

import com.StefanKuchta.BankApp.db.mapper.AccountRowMapper;
import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.db.service.functions.IbanGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


@Component
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper accountRowMapper = new AccountRowMapper();


    @Autowired
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> getAllAccounts() {
        final String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, accountRowMapper);
    }

    public Account getAccountById(long id) {
        final String sql = "SELECT * FROM account WHERE account.id = " + id;
        return jdbcTemplate.queryForObject(sql, accountRowMapper);
    }

    public String getIbanFromId(Long id) {
        final String sql = "SELECT iban FROM account WHERE account.id = " + id;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public boolean checkIfAccountExist(String iban) {
        final String sql = "SELECT iban FROM account WHERE account.iban = '" + iban + "'";
        try {
            jdbcTemplate.queryForObject(sql, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

//    public boolean checkIfAccountExist(String iban) {
//        final String sql = "SELECT iban FROM account WHERE account.iban = ?";
//        try {
//            jdbcTemplate.queryForObject(sql, new Object[] { iban }, String.class);
//            return true;
//        } catch (EmptyResultDataAccessException e) {
//            return false;
//        }
//    }

    public Long getIdFromIban(String iban) {
        final String sql = "SELECT id FROM account WHERE account.iban = '" + iban + "'";
        try {
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Double getBalance(long accountId) {
        final String sql = "SELECT balance FROM account WHERE account.id = " + accountId;
        try {
            return jdbcTemplate.queryForObject(sql, Double.class);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void setBalance(long accountId, double balance) {
        final String sql = "UPDATE account SET balance = ? WHERE account.id = ?";
        jdbcTemplate.update(sql, balance, accountId);
    }

    public Long createAccountAndReturnGeneratedId(Account account) {
        final String sql = "INSERT INTO account (user_id, iban, name, balance) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, account.getUserId());
            String iban = IbanGenerator.generateIban();
            if (checkIfAccountExist(iban)) {
                while (checkIfAccountExist(iban)) {
                    iban = IbanGenerator.generateIban();
                }
            }
            ps.setString(2, iban);
            ps.setString(3, account.getName());
            ps.setDouble(4, 0);
            return ps;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            return null;
        }
    }

    public Long createAccountWithUserCreate(Long id) {
        final String sql = "INSERT INTO account (user_id, iban, name, balance) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);
            String iban = IbanGenerator.generateIban();
            if (checkIfAccountExist(iban)) {
                while (checkIfAccountExist(iban)) {
                    iban = IbanGenerator.generateIban();
                }
            }
            ps.setString(2, iban);
            ps.setString(3, "bank account");
            ps.setDouble(4, 0);
            return ps;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            return null;
        }

    }

}
