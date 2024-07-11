package com.attraya.service;

import com.attraya.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleEmail(EmailRequest emailRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(emailRequest.getToEmail());
        mailMessage.setSubject(emailRequest.getSubject());
        mailMessage.setText(emailRequest.getMessageBody());
        javaMailSender.send(mailMessage);
        return "email successfully sent to :: " + emailRequest.getToEmail();
    }

    public String sendEmailWithAttachment(EmailRequest emailRequest) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(emailRequest.getToEMails());
        mimeMessageHelper.setSubject(emailRequest.getSubject());
        mimeMessageHelper.setText(emailRequest.getMessageBody());

        FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachment()));
        mimeMessageHelper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with attachment :: "+file.getFilename();
    }

}
