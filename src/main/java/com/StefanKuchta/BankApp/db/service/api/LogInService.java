package com.StefanKuchta.BankApp.db.service.api;


import com.StefanKuchta.BankApp.db.service.api.response.LogInUserResponse;
import com.StefanKuchta.BankApp.domain.LogInData;

public interface LogInService {
    LogInUserResponse logInRequest(LogInData logInData);
}
