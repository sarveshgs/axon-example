package com.tw.axonbootcamp.query.handler;

import com.tw.axonbootcamp.domain.event.PhoneNumberUpdatedEvent;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.view.AccountView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberUpdatedEventHandlerTest {

    private static final String ID = "1";
    private static final String FIRSTNAME = "Ram,";
    private static final String PHONENUMBER = "12345676";
    private static final String PHONENUMBERNew = "11212345676";

    @Mock
    private
    AccountViewRepository accountViewRepository;

    @Test
    public void shouldUpdateAccountPhoneNumber() throws Exception {
        when(accountViewRepository.findOne(ID)).thenReturn(new AccountView(ID, FIRSTNAME, PHONENUMBER));
        PhoneNumberUpdatedEventHandler phoneNumberUpdatedEventHandler = new PhoneNumberUpdatedEventHandler(accountViewRepository);
        phoneNumberUpdatedEventHandler.on(new PhoneNumberUpdatedEvent(ID, PHONENUMBERNew));
        verify(accountViewRepository).save(any(AccountView.class));

    }
}