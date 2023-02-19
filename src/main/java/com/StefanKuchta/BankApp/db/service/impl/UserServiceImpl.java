package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.UserRepository;
import com.StefanKuchta.BankApp.domain.User;
import com.StefanKuchta.BankApp.db.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Long addUserAndReturnId(User user) {
        Long userId = userRepository.addUserAndReturnId(user);
        Long accountId = accountRepository.createAccountWithUserCreate(userId);
        return userId;
    }
}
