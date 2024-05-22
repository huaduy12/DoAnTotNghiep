package com.example.client_toeic.service.mail;

public interface SendMailService {

    Boolean sendMail(String recipient, String body, String subject);
}
