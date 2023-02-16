package com.StefanKuchta.BankApp.controller;

import com.StefanKuchta.BankApp.domain.User;
import com.StefanKuchta.BankApp.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody User user) {
        Integer id = userService.addUserAndReturnId(user);
        if(id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}








































