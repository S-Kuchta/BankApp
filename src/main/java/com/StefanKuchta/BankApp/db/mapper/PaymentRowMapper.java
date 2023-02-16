package com.StefanKuchta.BankApp.db.mapper;

import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentRowMapper implements RowMapper<Payment> {

    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();

        payment.setId(rs.getInt("id"));
        payment.setAccountId(rs.getInt("account_id"));
        payment.setIban(rs.getString("iban"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setInformation(rs.getString("information"));
        payment.setPayedAt(rs.getTimestamp("payedAt"));
        return payment;

    }


}