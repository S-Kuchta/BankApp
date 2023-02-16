package com.StefanKuchta.BankApp.service.api;

import com.StefanKuchta.BankApp.domain.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    @Nullable
    User getUserById(int id);

    @Nullable
    Integer addUserAndReturnId(User user);
}
