package com.example.client_toeic.service.mail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailId;
    @Override
    public Boolean sendMail(String recipient, String body, String subject) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(fromEmailId);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body, true); // Đặt tham số thứ hai là true để báo cho MimeMessageHelper biết rằng nội dung là HTML
            javaMailSender.send(mimeMessage);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Có lỗi trong việc gửi mail");
        }
    }
}
