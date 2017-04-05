package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;

public class CreateAccountCommanHandlerTest {
    private static final String ID = "1";
    private static final String FIRSTNAME = "Joy";
    private static final String PHONENUMBER = "872356378";
    private FixtureConfiguration<Account> fixture = newGivenWhenThenFixture(Account.class);

    @Before
    public void setUp() {
        CreateAccountCommandHandler createAccountHandler = new CreateAccountCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(createAccountHandler);
    }

    @Test
    public void shouldEmitAccountCreatEvent() {

        fixture.given()
                .when(new CreateAccountCommand(ID, FIRSTNAME, PHONENUMBER))
                .expectVoidReturnType()
                .expectEvents(new AccountCreatedEvent(ID, FIRSTNAME, PHONENUMBER));

    }
}