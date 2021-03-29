package com.mailservice.service;

import com.mailservice.dto.UserDTO;

public interface IEmailService {
    void sendSimpleMessage(UserDTO userDTO);
}
