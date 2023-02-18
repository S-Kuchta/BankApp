package com.StefanKuchta.BankApp.db.service.api;

import com.StefanKuchta.BankApp.domain.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    @Nullable
    User getUserById(long id);

    @Nullable
    Long addUserAndReturnId(User user);
}
