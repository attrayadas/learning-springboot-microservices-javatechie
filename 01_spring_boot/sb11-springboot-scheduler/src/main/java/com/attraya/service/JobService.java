package com.attraya.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Service
@EnableScheduling
@AllArgsConstructor
public class JobService {

    private NotificationService notificationService;

//    @Scheduled(fixedRate = 10000)
    @Scheduled(cron = "${cron_interval}", zone = "IST")
    public void process(){
        System.out.println("JobService.process :: Job Started on >> "+new Date());
        try {
            notificationService.sendDailyReports();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
