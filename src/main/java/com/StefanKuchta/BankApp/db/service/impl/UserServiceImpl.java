package com.StefanKuchta.BankApp.db.service.impl;

import com.StefanKuchta.BankApp.db.repository.AccountRepository;
import com.StefanKuchta.BankApp.db.repository.CentralIbanDbRepository;
import com.StefanKuchta.BankApp.db.repository.UserRepository;
import com.StefanKuchta.BankApp.domain.User;
import com.StefanKuchta.BankApp.db.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CentralIbanDbRepository centralIbanDbRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository, CentralIbanDbRepository centralIbanDbRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.centralIbanDbRepository = centralIbanDbRepository;
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
        centralIbanDbRepository.addIbanToCentralIbanDb(accountRepository.getIbanFromId(accountId));
        return userId;
    }
}
