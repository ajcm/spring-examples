package com.example.accounts;

public class AccountService {

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    private AccountRepository accountRepository;

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
