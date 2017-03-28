package com.tw.axonbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    private String firstname;
    private String phonenumber;

    public AccountRequest() {
    }

    public AccountRequest(@JsonProperty("firstname") String firstname, @JsonProperty("phonenumber") String phonenumber) {
        this.firstname = firstname;
        this.phonenumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
