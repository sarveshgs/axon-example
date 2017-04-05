package com.tw.axonbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePhoneNumberRequest {

    private String phonenumber;

    public UpdatePhoneNumberRequest() {
    }

    public UpdatePhoneNumberRequest(@JsonProperty("phonenumber") String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


}
