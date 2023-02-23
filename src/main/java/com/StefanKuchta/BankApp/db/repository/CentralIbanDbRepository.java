package com.StefanKuchta.BankApp.db.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Component
public class CentralIbanDbRepository {

    JdbcTemplate jdbcTemplate;


    public CentralIbanDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean checkIfIbanExist(String iban) {
        final String sql = "SELECT iban FROM central_iban_db WHERE central_iban_db.iban = '" + iban + "'";
        try {
            jdbcTemplate.queryForObject(sql, String.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void addIbanToCentralIbanDb(String iban) {
        final String sql = "INSERT INTO central_iban_db(iban) VALUES (?)";

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, iban);
            return ps;
        });
    }




}
