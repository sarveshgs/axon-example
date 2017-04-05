package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountCommandHandler {
    private Repository<Account> repository;

    @Autowired
    CreateAccountCommandHandler(Repository<Account> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateAccountCommand createAccountCommand) {
        Account account = new Account(createAccountCommand.getId(), createAccountCommand.getFirstname(), createAccountCommand.getPhonenumber());
      repository.add(account);
    }

}
