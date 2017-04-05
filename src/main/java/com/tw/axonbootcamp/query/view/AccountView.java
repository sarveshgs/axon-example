package com.tw.axonbootcamp.query.view;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountView {
    @Id
    private  String accountID;
    private  String firstname;
    private  String phonenumber;

    public AccountView(String accountID, String firstname, String phonenumber) {
        this.accountID = accountID;
        this.firstname = firstname;
        this.phonenumber = phonenumber;
    }

    public AccountView() {
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
