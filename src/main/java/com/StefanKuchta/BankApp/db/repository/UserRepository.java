package com.StefanKuchta.BankApp.db.repository;

import com.StefanKuchta.BankApp.db.mapper.UserRowMapper;
import com.StefanKuchta.BankApp.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserById(long id) {
        final String sql = "SELECT * FROM user WHERE user.id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        final String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public Long addUserAndReturnId(User user) {
        final String sql = "INSERT INTO user(name, surname, email, tel_number) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getTelNumber());
                ps.setString(5, user.getPassword());
                return ps;
            }
        }, keyHolder);
        if(keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            return null;
        }
    }

}









































