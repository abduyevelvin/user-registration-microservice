package com.mailservice.service;

import com.mailservice.dto.UserDTO;
import com.mailservice.entity.Mail;
import com.mailservice.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements IEmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public MailRepository mailRepository;

    @Override
    public void sendSimpleMessage(UserDTO userDTO) {
        try {
            Mail newMail = new Mail();
            newMail.setTo(userDTO.getUsername());
            newMail.setSubject("Test Subject");
            newMail.setText("Test Text");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(newMail.getTo());
            message.setSubject(newMail.getSubject());
            message.setText(newMail.getText());

            mailRepository.save(newMail);
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
