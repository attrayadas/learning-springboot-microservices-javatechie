package com.attraya.contributer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationContextContributor implements InfoContributor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("bean-definition-count", applicationContext.getBeanDefinitionCount());
        contextMap.put("bean-names", applicationContext.getBeanDefinitionNames());
        contextMap.put("application-startup-time", applicationContext.getStartupDate());
        builder.withDetail("context", contextMap);

    }
}
