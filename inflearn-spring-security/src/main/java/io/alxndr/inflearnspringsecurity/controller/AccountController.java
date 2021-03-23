package io.alxndr.inflearnspringsecurity.controller;

import io.alxndr.inflearnspringsecurity.account.Account;
import io.alxndr.inflearnspringsecurity.account.AccountRepository;
import io.alxndr.inflearnspringsecurity.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Alexander Choi
 * @date : 2021/03/23
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {
        return accountService.createAccount(account);
    }

}
