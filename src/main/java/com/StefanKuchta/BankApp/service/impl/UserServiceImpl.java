package com.StefanKuchta.BankApp.service.impl;

import com.StefanKuchta.BankApp.db.repository.UserRepository;
import com.StefanKuchta.BankApp.domain.User;
import com.StefanKuchta.BankApp.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private User user;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Integer addUserAndReturnId(User user) {
        return userRepository.addUserAndReturnId(user);
    }
}