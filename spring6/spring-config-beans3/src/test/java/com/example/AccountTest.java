package com.example;

import com.example.accounts.AccountConfiguration;
import com.example.accounts.AccountService;
import com.example.accounts.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = AccountConfiguration.class)
@SpringJUnitConfig(classes = AccountConfiguration.class)
public class AccountTest {

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    TransferService transferService;

    @Autowired
    AccountService accountService;

    @Test
    void test(@Autowired TransferService transferService, @Autowired AccountService accountService) {

        System.out.println(accountService.getAccountRepository());
        System.out.println(transferService.getAccountRepository());

    }


}
