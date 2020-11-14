package io.alxndr.demospringsecurity;

import io.alxndr.demospringsecurity.account.Account;
import io.alxndr.demospringsecurity.account.AccountRepository;
import io.alxndr.demospringsecurity.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccounRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account newAccount = accountService.createAccount("alxndr", "1234");
        System.out.println(newAccount.getUsername() + "/" + newAccount.getPassword());
    }
}
