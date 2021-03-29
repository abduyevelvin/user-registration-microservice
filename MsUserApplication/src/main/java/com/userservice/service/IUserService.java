package com.userservice.service;

import com.userservice.entity.User;

public interface IUserService {
    User registerUser(User user);
    Iterable<User> allUsers();
}
