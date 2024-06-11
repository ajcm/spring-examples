package com.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {
    private Mail configMail;

    //get executed after bean initialization
    @Autowired
    private void setUp() {
        configMail = new Mail("To me", "From to", "Subject");
    }

    @Bean
    @Primary
    public Mail getConfigMail() {
        return configMail;
    }


    // getter method
    // bean can be retrieved without params
    @Bean(name = "myEmail")
    public Mail getMyEmail(@Value("${mail.to}") String to,
                           @Value("${mail.from}") String from,
                           @Value("${mail.subject}") String subject) {
        return new Mail(to, from, subject);
    }

}
