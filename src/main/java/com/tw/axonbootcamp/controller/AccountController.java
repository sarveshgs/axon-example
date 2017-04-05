package com.tw.axonbootcamp.controller;

import com.tw.axonbootcamp.domain.exception.InvalidUpdateOperationException;
import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.dto.UpdatePhoneNumberRequest;
import com.tw.axonbootcamp.query.exception.AccountNotFoundException;
import com.tw.axonbootcamp.query.view.AccountView;
import com.tw.axonbootcamp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AccountController {

    private final AccountService accountService;


    @Autowired
    AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @PostMapping(path = "/accounts")
    public DeferredResult create(@RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @GetMapping(path = "/accounts/{accountId}")
    @ResponseBody
    public AccountView get(@PathVariable String accountId) throws AccountNotFoundException {
        return accountService.get(accountId);
    }

    @PutMapping(path = "/accounts/{accountId}")
    @ResponseBody
    public DeferredResult update(@PathVariable String accountId, @RequestBody UpdatePhoneNumberRequest updatePhoneNumberRequest) throws AccountNotFoundException {
        System.out.println("phone number "+updatePhoneNumberRequest.getPhonenumber());
        return accountService.update(accountId, updatePhoneNumberRequest);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This Account is not found in the system")
    @ExceptionHandler(AccountNotFoundException.class)
    public void accountNotFoundHandler() {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Phone number update not allowed as previous phone number is same as current phone number")
    @ExceptionHandler(InvalidUpdateOperationException.class)
    public void invalidUpdateOperationHandler() {

    }


}
