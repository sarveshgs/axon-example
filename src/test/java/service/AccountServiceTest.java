package service;

import com.tw.axonbootcamp.controller.AccountController;
import com.tw.axonbootcamp.domain.command.CreateAccountCommand;
import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.service.AccountService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    CommandGateway commandGateway;

    @Test
    public void shouldCreateCommandOnAccountCreation() throws Exception {

        AccountService service = new AccountService(commandGateway);
        AccountRequest accountRequest = new AccountRequest("Ram", "12345678");
        service.create(accountRequest);
        verify(commandGateway).send(Matchers.any(CreateAccountCommand.class));

    }


}
