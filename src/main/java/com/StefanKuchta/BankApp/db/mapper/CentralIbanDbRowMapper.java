package com.StefanKuchta.BankApp.db.mapper;

import com.StefanKuchta.BankApp.domain.CentralIbanDb;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CentralIbanDbRowMapper implements RowMapper<CentralIbanDb> {

    public CentralIbanDb mapRow(ResultSet rs, int rowNum) throws SQLException {
        CentralIbanDb centralIbanDb = new CentralIbanDb();
        centralIbanDb.setId(rs.getLong("id"));
        centralIbanDb.setIban(rs.getString("iban"));
        return centralIbanDb;
    }
}
