package com.tw.axonbootcamp.domain.command;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class CreateAccountCommand {
    private final String firstname;
    private final String phonenumber;

    public CreateAccountCommand(String firstname, String phonenumber) {
        this.firstname = firstname;
        this.phonenumber = phonenumber;
    }

}
