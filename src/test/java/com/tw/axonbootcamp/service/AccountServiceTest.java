package com.tw.axonbootcamp.service;

import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.domain.command.UpdatePhoneNumberCommand;
import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.dto.UpdatePhoneNumberRequest;
import com.tw.axonbootcamp.query.AccountViewRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private
    CommandGateway commandGateway;
    @Mock
    private
    AccountViewRepository accountViewRepository;

    @Test
    public void shouldCreateCommandOnAccountCreation() throws Exception {
        AccountService service = new AccountService(commandGateway, accountViewRepository);
        AccountRequest accountRequest = new AccountRequest("Ram", "12345678");
        service.create(accountRequest);
        verify(commandGateway).send(Matchers.any(CreateAccountCommand.class), Matchers.any(AccountCommandCallBack.class));
    }

    @Test
    public void shouldCreateCommandOnAccountCreation2() throws Exception {
        AccountService service = new AccountService(commandGateway, accountViewRepository);
        String phonenumber = "12345678";
        UpdatePhoneNumberRequest accountRequest = new UpdatePhoneNumberRequest(phonenumber);
        service.update("1", accountRequest);
        verify(commandGateway).send(Matchers.any(UpdatePhoneNumberCommand.class), Matchers.any(AccountCommandCallBack.class));
    }
}
