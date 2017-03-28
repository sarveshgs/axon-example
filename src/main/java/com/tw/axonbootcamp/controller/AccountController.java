package com.tw.axonbootcamp.controller;

import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/accounts")
    public void create(AccountRequest accountRequest) {
        accountService.create(accountRequest);
    }
}
