package com.zian.travelo.service;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body);
}
