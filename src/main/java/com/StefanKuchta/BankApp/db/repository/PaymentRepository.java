package com.StefanKuchta.BankApp.db.repository;

import com.StefanKuchta.BankApp.db.mapper.PaymentRowMapper;
import com.StefanKuchta.BankApp.domain.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PaymentRowMapper paymentRowMapper = new PaymentRowMapper();

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Payment> getAllPayments() {
        final String sql = "SELECT * FROM payment";
        return jdbcTemplate.query(sql,paymentRowMapper);
    }


//    public Long addPaymentToPaymentHistory(Payment payment, long accountId) {
//        final String sql = "INSERT INTO payment(account_id, payer_iban, receiver_iban, amount, information, variable_number, payed_at, type) VALUES (?,?,?,?,?,?,?,?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update((Connection con) -> {
//            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setLong(1, accountId);
//            ps.setString(2, payment.getPayerIban());
//            ps.setString(3, payment.getReceiverIban());
//            ps.setDouble(4, payment.getAmount());
//            ps.setString(5, payment.getInformation());
//            ps.setString(6, payment.getVariableNumber());
//            if(payment.getPayedAt() == null) {
//                payment.setPayedAt(Timestamp.from(Instant.now()));
//            }
//            ps.setTimestamp(7, payment.getPayedAt());
//            ps.setString(8, payment.getType());
//            return ps;
//        }, keyHolder);
//        if(keyHolder.getKey() != null) {
//            return keyHolder.getKey().longValue();
//        } else {
//            return null;
//        }
//    }

    public Long addPaymentToPaymentHistory(Payment payment, long accountId, String type) {
        final String sql = "INSERT INTO payment(account_id, payer_iban, receiver_iban, amount, information, variable_number, payed_at, type) VALUES (?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, accountId);
            ps.setString(2, payment.getPayerIban());
            ps.setString(3, payment.getReceiverIban());
            ps.setDouble(4, payment.getAmount());
            ps.setString(5, payment.getInformation());
            ps.setString(6, payment.getVariableNumber());
            if(payment.getPayedAt() == null) {
                payment.setPayedAt(Timestamp.from(Instant.now()));
            }
            ps.setTimestamp(7, payment.getPayedAt());
            ps.setString(8, type);
            return ps;
        }, keyHolder);
        if(keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            return null;
        }
    }
}
