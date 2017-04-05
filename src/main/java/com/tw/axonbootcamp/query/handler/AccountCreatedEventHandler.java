package com.tw.axonbootcamp.query.handler;

import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.view.AccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedEventHandler {

    private AccountViewRepository repository;

    @Autowired
    public AccountCreatedEventHandler(AccountViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        repository.save(new AccountView(event.getId(), event.getFirstname(), event.getPhonenumber()));
    }
}
