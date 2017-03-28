package com.tw.axonbootcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.axonbootcamp.domain.Account;
import com.tw.axonbootcamp.dto.AccountRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.tw.axonbootcamp.service.AccountService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AccountService accountService;

    @Test
    public void shouldReturnSuccessOnAccountCreation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AccountRequest accountRequest = new AccountRequest("Ram", "12345678");
        mvc.perform(post("/accounts").content(mapper.writeValueAsString(accountRequest))).andExpect(status().isOk());
    }

    @Test
    public void shouldCreateAccountWithParams() throws Exception {
        AccountController accountController = new AccountController(accountService);
        AccountRequest accountRequest = new AccountRequest("Ram", "12345678");
        accountController.create(accountRequest);
        verify(accountService).create(accountRequest);

    }
}