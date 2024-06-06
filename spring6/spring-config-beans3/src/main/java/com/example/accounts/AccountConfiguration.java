package com.example.accounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AccountConfiguration {

    @Bean
    @Scope("prototype")
    public AccountRepository accountRepository() {
        System.out.println("accountRepository()");
        return new AccountRepository();
    }

    @Bean
    public AccountService accountService() {
        System.out.println("accountService()");
        return new AccountService(accountRepository());
    }


    @Bean
    public TransferService transferService() {
        System.out.println("transferService()");
        return new TransferService(accountRepository());
    }

}
