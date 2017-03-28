package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountHandler {
    private Repository repository;

    public CreateAccountHandler(Repository repository) {

        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateAccountCommand accountCommand) {


    }

}
