package com.attraya.bean;

public class SocialMediaFactory {

    public static SocialMediaService getInstance(String socialMediaType){
        if (socialMediaType.equals("facebook")){
            return new FaceBookService();
        } else if (socialMediaType.equals("instagram")){
            return new InstagramService();
        } else {
            return null;
        }
    }
}
