package com.attraya.controller;

import com.attraya.dto.EmailRequest;
import com.attraya.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendTextEmail(@RequestBody EmailRequest emailRequest){
        return emailService.sendSimpleEmail(emailRequest);
    }

    @PostMapping("/sendAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) throws MessagingException {
        return emailService.sendEmailWithAttachment(emailRequest);
    }
}
