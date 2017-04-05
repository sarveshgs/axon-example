package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.domain.command.UpdatePhoneNumberCommand;
import com.tw.axonbootcamp.domain.exception.InvalidUpdateOperationException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdatePhoneNumberCommandHandler {

    private final Repository<Account> repository;

    @Autowired
    public UpdatePhoneNumberCommandHandler(Repository<Account> repository) {
        this.repository = repository;
    }


    @CommandHandler
    public void handle(UpdatePhoneNumberCommand updatePhoneNumberCommand) throws InvalidUpdateOperationException {
        Account account = repository.load(updatePhoneNumberCommand.getAccountId());
        account.updatePhoneNumber(updatePhoneNumberCommand.getPhonenumber());
    }
}
