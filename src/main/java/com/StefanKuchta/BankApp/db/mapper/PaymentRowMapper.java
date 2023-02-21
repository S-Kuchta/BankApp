package com.StefanKuchta.BankApp.db.mapper;

import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentRowMapper implements RowMapper<Payment> {

    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();

        payment.setId(rs.getLong("id"));
        payment.setAccountId(rs.getLong("account_id"));
        payment.setPayerIban(rs.getString("payer_iban"));
        payment.setReceiverIban(rs.getString("receiver_iban"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setInformation(rs.getString("information"));
        payment.setVariableNumber(rs.getString("variable_number"));
        payment.setPayedAt(rs.getTimestamp("payed_at"));
        payment.setType(rs.getString("type"));

        return payment;

    }


}
