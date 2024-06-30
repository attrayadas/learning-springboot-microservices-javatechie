package com.attraya.bean;

public class FaceBookService implements SocialMediaService{
    @Override
    public void getUserFeeds() {
        System.out.println("Loading user feeds from FaceBook...");
    }
}
