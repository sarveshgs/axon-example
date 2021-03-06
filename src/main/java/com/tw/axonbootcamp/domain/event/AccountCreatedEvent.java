package com.tw.axonbootcamp.domain.event;

public class AccountCreatedEvent {

    private final String id;
    private final String firstname;
    private final String phonenumber;

    public AccountCreatedEvent(String id, String firstname, String phonenumber) {
        this.id = id;
        this.firstname = firstname;
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
