package com.example;

import com.example.mail.Mail;
import com.example.mail.MailConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MailConfiguration.class)
public class MailConfigurationTest {

    /**
     * Complex example with the use of annotations and autowire
     */
    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void test() {
        Mail myMail = (Mail) applicationContext.getBean("myEmail");
        Assertions.assertNotNull(myMail);

        Assertions.assertEquals(myMail.getTo(), "To me");
    }

    @Test
    public void test2() {
        Mail myMail = applicationContext.getBean(Mail.class);
        Assertions.assertNotNull(myMail);

        Assertions.assertEquals(myMail.getTo(), "To me");
    }


}
