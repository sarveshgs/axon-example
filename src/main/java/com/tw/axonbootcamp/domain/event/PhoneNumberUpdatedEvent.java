package com.tw.axonbootcamp.domain.event;

public class PhoneNumberUpdatedEvent {
    private final String id;
    private final String phonenumber;

    public PhoneNumberUpdatedEvent(String id, String phonenumber) {
        this.id = id;
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
