package com.example;

import org.springframework.beans.factory.annotation.Value;

public class ProfileManager {
    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    public String getActiveProfiles() {
        for (String profileName : activeProfiles.split(",")) {
            System.out.println("Currently active profile - " + profileName);
        }

        return activeProfiles;
    }
}