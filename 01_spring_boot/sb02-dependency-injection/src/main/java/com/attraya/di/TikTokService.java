package com.attraya.di;

import org.springframework.stereotype.Service;

@Service
public class TikTokService implements SocialAppService{

    public TikTokService() {
        System.out.println("Object created of TikTokService");
    }

    @Override
    public void getUserFeeds() {
        System.out.println("Feed loaded from TikTok!");
    }
}
