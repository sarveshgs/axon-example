package com.tw.axonbootcamp.domain;

import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import com.tw.axonbootcamp.domain.event.PhoneNumberUpdatedEvent;
import com.tw.axonbootcamp.domain.exception.InvalidUpdateOperationException;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

public class Account extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String identifier;
    private String firstname;
    private String phonenumber;

    public Account() {
    }

    public Account(String id, String firstname, String phonenumber) {
        apply(new AccountCreatedEvent(id, firstname, phonenumber));
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }


    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.identifier = event.getId();
        this.firstname = event.getFirstname();
        this.phonenumber = event.getPhonenumber();
    }

    @EventSourcingHandler
    public void on(PhoneNumberUpdatedEvent event) {
        this.phonenumber = event.getPhonenumber();
    }

    public void updatePhoneNumber(String phonenumber) throws InvalidUpdateOperationException {
        if (phonenumber.equals(this.phonenumber)) {
            throw new InvalidUpdateOperationException();
        }
        apply(new PhoneNumberUpdatedEvent(this.identifier, phonenumber));
    }
}
