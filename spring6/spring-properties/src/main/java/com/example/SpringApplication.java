package com.example;

import com.example.service.ProfileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    /**
     * VM Options: -Dspring.profiles.active=dev
     *
     * @param args
     */

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        String profile = (String) applicationContext.getBean("activeProfile");
        System.out.println("profile ->" + profile);

        String messageBean = (String) applicationContext.getBean("welcome");
        System.out.println("message ->" + messageBean);

        String key = (String) applicationContext.getBean("key");
        System.out.println("key ->" + key);

        ProfileManager profileManager = applicationContext.getBean("profileManager", ProfileManager.class);
        System.out.println("profileManager ->" + profileManager.getActiveProfiles());

    }
}
