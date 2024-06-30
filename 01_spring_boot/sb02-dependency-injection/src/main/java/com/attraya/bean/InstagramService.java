package com.attraya.bean;

public class InstagramService implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("Loading user feeds from Instagram...");
    }
}
