package com.mailservice.kafka.consumer;

import com.mailservice.dto.UserDTO;
import com.mailservice.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private IEmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.userCreated}")
    public void receive(UserDTO payload) {
        LOGGER.info("received payload='{}'", payload);
        emailService.sendSimpleMessage(payload);
        latch.countDown();
    }
}
