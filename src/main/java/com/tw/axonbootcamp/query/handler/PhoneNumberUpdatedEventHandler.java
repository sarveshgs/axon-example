package com.tw.axonbootcamp.query.handler;

import com.tw.axonbootcamp.domain.event.PhoneNumberUpdatedEvent;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.view.AccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberUpdatedEventHandler {

    private AccountViewRepository accountViewRepository;

    @Autowired
    public PhoneNumberUpdatedEventHandler(AccountViewRepository accountViewRepository) {

        this.accountViewRepository = accountViewRepository;
    }

    @EventHandler
    public void on(PhoneNumberUpdatedEvent phoneNumberUpdatedEvent) {
        AccountView accountView = accountViewRepository.findOne(phoneNumberUpdatedEvent.getId());
        accountView.setPhonenumber(phoneNumberUpdatedEvent.getPhonenumber());
        accountViewRepository.save(accountView);
    }
}
