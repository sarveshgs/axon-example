package com.tw.axonbootcamp.domain;

import com.tw.axonbootcamp.domain.exception.InvalidUpdateOperationException;
import org.junit.Test;

public class AccountTest {
    private static final String ID = "1";
    private static final String FIRSTNAME = "Joy";
    private static final String PHONENUMBER = "872356378";

    @Test(expected = InvalidUpdateOperationException.class)
    public void shouldShowExceptionIfPreviousAndNewPhoneIsSame() throws Exception {
        Account account = new Account(ID, FIRSTNAME, PHONENUMBER);
        account.updatePhoneNumber(PHONENUMBER);

    }
}
