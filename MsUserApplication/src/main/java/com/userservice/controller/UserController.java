package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/members")
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userService.allUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
