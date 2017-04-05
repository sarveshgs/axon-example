package com.tw.axonbootcamp.service;


import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.domain.command.UpdatePhoneNumberCommand;
import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.dto.AccountResponse;
import com.tw.axonbootcamp.dto.UpdatePhoneNumberRequest;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.exception.AccountNotFoundException;
import com.tw.axonbootcamp.query.view.AccountView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class AccountService {
    private CommandGateway commandGateway;
    private AccountViewRepository accountViewRepository;

    @Autowired
    AccountService(CommandGateway commandGateway, AccountViewRepository accountViewRepository) {
        this.commandGateway = commandGateway;
        this.accountViewRepository = accountViewRepository;
    }

    public AccountView get(@PathVariable String accountId) throws AccountNotFoundException {
        AccountView account = accountViewRepository.findOne(accountId);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    public AccountCommandCallBack create(AccountRequest accountRequest) {
        String id = UUID.randomUUID().toString();
        AccountCommandCallBack callBack = new AccountCommandCallBack<AccountResponse>(new AccountResponse(id));
        commandGateway.send(new CreateAccountCommand(id, accountRequest.getFirstname(), accountRequest.getPhonenumber()), callBack);
        return callBack;
    }

    public AccountCommandCallBack update(String accountId, UpdatePhoneNumberRequest updatePhoneNumberRequest) {
        AccountCommandCallBack callBack = new AccountCommandCallBack<AccountResponse>(new AccountResponse(accountId));
        commandGateway.send(new UpdatePhoneNumberCommand(accountId, updatePhoneNumberRequest.getPhonenumber()), callBack);
        return callBack;
    }
}



