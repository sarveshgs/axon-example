package com.tw.axonbootcamp.dto;

public class AccountResponse {

    private final String accountID;

    public AccountResponse(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountID() {
        return accountID;
    }
}
