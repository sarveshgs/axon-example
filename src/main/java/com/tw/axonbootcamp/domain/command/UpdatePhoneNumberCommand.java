package com.tw.axonbootcamp.domain.command;

public class UpdatePhoneNumberCommand {

    private final String accountId;
    private final String phonenumber;

    public UpdatePhoneNumberCommand(String accountId, String phonenumber) {
        this.accountId = accountId;
        this.phonenumber = phonenumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
