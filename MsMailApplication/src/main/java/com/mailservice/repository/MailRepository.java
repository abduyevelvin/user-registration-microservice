package com.mailservice.repository;

import com.mailservice.entity.Mail;
import org.springframework.data.repository.CrudRepository;

public interface MailRepository extends CrudRepository<Mail, Long> {
}
