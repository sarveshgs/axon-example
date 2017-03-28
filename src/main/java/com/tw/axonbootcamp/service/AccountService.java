package com.tw.axonbootcamp.service;


import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.dto.AccountRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private CommandGateway commandGateway;

    @Autowired
    public AccountService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void create(AccountRequest accountRequest) {
        commandGateway.send(new CreateAccountCommand(accountRequest.getFirstname(), accountRequest.getPhonenumber()));
    }
}
