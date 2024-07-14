package com.attraya.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * Sends mail with the Excel file generated
 */
@Service
public class NotificationService {

    @Autowired
    private ReportService reportService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${report.send.email.toList}")
    private String toEmails;

    public String sendDailyReports() throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(toEmails.split(","));
        mimeMessageHelper.setSubject("List of Orders "+new Date().getTime());
        mimeMessageHelper.setText("Hi User,\n\nPlease find the attachment for today's order received! \n\nThanks and Regards\nAttraya");

        byte[] report = reportService.generateReport();

        ByteArrayResource content = new ByteArrayResource(report);
        mimeMessageHelper.addAttachment(new Date().getTime()+"_orders.xlsx", content);

        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with attachment!";
    }

}
