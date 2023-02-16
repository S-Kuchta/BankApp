package com.StefanKuchta.BankApp.db.repository;

import com.StefanKuchta.BankApp.db.mapper.AccountRowMapper;
import com.StefanKuchta.BankApp.domain.Account;
import com.StefanKuchta.BankApp.service.functions.IbanGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper accountRowMapper = new AccountRowMapper();
    private final IbanGenerator ibanGenerator = new IbanGenerator();

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> getAllAccounts() {
        final String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, accountRowMapper);
    }

    public Account getAccountById(int id) {
        final String sql = "SELECT * FROM account WHERE account.id = " + id;
        return jdbcTemplate.queryForObject(sql, accountRowMapper);
    }

    public Integer createAccount(Account account) {
        final String sql = "INSERT INTO account (user_id, iban, name, balance) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, account.getUserId());
                ps.setString(2, ibanGenerator.generateIban());
                ps.setString(3, account.getName());
                ps.setDouble(4, 0);
                return ps;
            }
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

}
