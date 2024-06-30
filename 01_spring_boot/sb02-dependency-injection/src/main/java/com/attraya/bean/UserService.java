package com.attraya.bean;

public class UserService {

    private SocialMediaService socialMediaService;

    /*public SocialMediaService getSocialMediaService() {
        return socialMediaService;
    }

    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }*/

    // Approach 5: Constructor Dependency
    public UserService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    public void displayFeed(){
        // Approach 1:
        /*SocialMediaApp socialMediaApp = new SocialMediaApp();
        FaceBookService faceBookService = new FaceBookService();
        InstagramService instagramService = new InstagramService();
        socialMediaApp.getUserFeeds();*/

        // Approach 2:
        /*SocialMediaService service = new InstagramService();
        service.getUserFeeds();*/

        // Approach 3 (depends on class/bean alias name):
        /*SocialMediaService instagram = SocialMediaFactory.getInstance("instagram");
        instagram.getUserFeeds();*/

        // Approach 4: (Setter Injection)
//        socialMediaService.getUserFeeds();
    }

    public static void main(String[] args) {
        UserService userService = new UserService(new InstagramService());
//        userService.setSocialMediaService(new InstagramService());
        userService.displayFeed();
    }
}
