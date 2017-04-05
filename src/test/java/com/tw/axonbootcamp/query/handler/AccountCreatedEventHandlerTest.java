package com.tw.axonbootcamp.query.handler;


import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.view.AccountView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreatedEventHandlerTest {

    private static final String ID = "1";
    private static final String FIRSTNAME = "Ram,";
    private static final String PHONENUMBER = "12345676";

    @Mock
    private
    AccountViewRepository accountViewRepository;


    @Test
    public void shouldSaveAccountOnAccountCreation() throws Exception {
        AccountCreatedEventHandler accountCreatedEventHandler = new AccountCreatedEventHandler(accountViewRepository);
        accountCreatedEventHandler.on(new AccountCreatedEvent(ID, FIRSTNAME, PHONENUMBER));
        verify(accountViewRepository).save(any(AccountView.class));
    }

}
