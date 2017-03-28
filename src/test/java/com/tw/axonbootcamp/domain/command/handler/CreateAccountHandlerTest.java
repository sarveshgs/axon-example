package com.tw.axonbootcamp.domain.command.handler;

import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.domain.event.AccountCreatedEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import static org.axonframework.test.Fixtures.newGivenWhenThenFixture;
import static org.junit.Assert.*;

public class CreateAccountHandlerTest {
    private FixtureConfiguration fixture;

    @Before
    public void setUp() {
        fixture = newGivenWhenThenFixture(Account.class);
        fixture.registerAnnotatedCommandHandler(
                new CreateAccountHandler(fixture.getRepository())
        );
    }

    @Test
    public void testFirstFixture() {
        fixture.given()
                .when(new CreateAccountCommand("ram", "12345"))
                .expectVoidReturnType()
                .expectEvents(new AccountCreatedEvent());
    }
}