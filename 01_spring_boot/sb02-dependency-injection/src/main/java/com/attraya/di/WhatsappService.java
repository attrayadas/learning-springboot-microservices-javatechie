package com.attraya.di;

import org.springframework.stereotype.Service;

@Service
public class WhatsappService implements SocialAppService{

    public WhatsappService() {
        System.out.println("Whatsapp object created...");
    }

    @Override
    public void getUserFeeds() {
        System.out.println("Load Feeds from Whatsapp!");
    }
}
