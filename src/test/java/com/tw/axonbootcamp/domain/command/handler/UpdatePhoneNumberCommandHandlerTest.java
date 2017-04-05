package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.domain.command.UpdatePhoneNumberCommand;
import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import com.tw.axonbootcamp.domain.event.PhoneNumberUpdatedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;

public class UpdatePhoneNumberCommandHandlerTest {
    private static final String ID = "1";
    private static final String PHONENUMBER = "872356378";
    private static final String PHONENUMBER_NEW = "1872356378";
    private FixtureConfiguration fixture;

    @Before
    public void setUp() {
        fixture = newGivenWhenThenFixture(Account.class);
        UpdatePhoneNumberCommandHandler updatePhoneNumberCommandHandler = new UpdatePhoneNumberCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(updatePhoneNumberCommandHandler);
    }

    @Test
    public void shouldEmitAccountCreatEvent() {

        fixture.given(new AccountCreatedEvent(ID, "", PHONENUMBER))
                .when(new UpdatePhoneNumberCommand(ID, PHONENUMBER_NEW))
                .expectVoidReturnType()
                .expectEvents(new PhoneNumberUpdatedEvent(ID, PHONENUMBER_NEW));

    }

}