package com.attraya.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserApp {

    // Field Injection
    /*@Autowired
    @Qualifier("whatsappService")
    private SocialAppService socialAppService;*/

    // Constructor Injection (cyclic dependency is not resolved in constructor dependency)
    private SocialAppService socialAppService;

    @Autowired
    public UserApp(@Qualifier("whatsappService") SocialAppService socialAppService){
        this.socialAppService = socialAppService;
    }

    public UserApp() {
        System.out.println("UserApp object created...");
    }

    public void loadUserFeeds(){
        socialAppService.getUserFeeds();
    }
}
