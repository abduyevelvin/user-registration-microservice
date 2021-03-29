package com.userservice.service;

import com.userservice.entity.User;
import com.userservice.kafka.producer.Sender;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {
    @Value("${spring.kafka.topic.userCreated}")
    private String USER_CREATED_TOPIC;
    private UserRepository userRepository;
    private Sender sender;

    @Autowired
    public UserService(UserRepository userRepository, Sender sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    @Override
    public User registerUser(User user) {
        User createdUser = userRepository.save(user);
        sender.send(USER_CREATED_TOPIC, createdUser);

        return createdUser;
    }

    @Override
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }
}
