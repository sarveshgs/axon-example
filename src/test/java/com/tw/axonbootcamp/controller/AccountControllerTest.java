package com.tw.axonbootcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.axonbootcamp.dto.AccountRequest;
import com.tw.axonbootcamp.dto.UpdatePhoneNumberRequest;
import com.tw.axonbootcamp.query.AccountViewRepository;
import com.tw.axonbootcamp.query.exception.AccountNotFoundException;
import com.tw.axonbootcamp.query.view.AccountView;
import com.tw.axonbootcamp.service.AccountCommandCallBack;
import com.tw.axonbootcamp.service.AccountService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class AccountControllerTest {

    private static final String NAME = "Ram";
    private static final String PHONE = "123456";


    private MockMvc mvc;

    @Mock
    AccountViewRepository accountViewRepository;
    @Mock
    private AccountService accountService;
    @Mock
    private
    AccountCommandCallBack accountCommandCallBack;
    private AccountController accountController;

    @Before
    public void setUp() throws Exception {
        accountController = new AccountController(accountService);
        mvc = MockMvcBuilders.standaloneSetup(accountController).build();

    }

    @Test
    public void shouldReturnSuccessOnAccountCreation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AccountRequest accountRequest = new AccountRequest(NAME, PHONE);
        mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(accountRequest)))
                .andExpect(status().isOk());
        verify(accountService).create(any(AccountRequest.class));
    }

    @Test
    @Ignore
    public void shouldReturnAccountIdOnAccountCreation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AccountRequest accountRequest = new AccountRequest(NAME, PHONE);
        MvcResult mvcResult = mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(jsonPath("accountID").isNotEmpty());
        verify(accountService).create(any(AccountRequest.class));
    }

    @Test
    public void shouldReturnAccountWithValidIdentifier() throws Exception {
        String accountId = "1";
        when(accountService.get(accountId)).thenReturn(new AccountView(accountId, NAME, PHONE));
        mvc.perform(get("/accounts/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstname").value(NAME))
                .andExpect(jsonPath("phonenumber").value(PHONE));
        verify(accountService).get(accountId);

    }

    @Test
    public void shouldThrowExceptionIfAccountNotFound() throws Exception {
        when(accountService.get("1")).thenThrow(new AccountNotFoundException());
        mvc.perform(get("/accounts/{accountId}", "1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldUpdatePhoneNumberForGivenAccount() throws Exception {
        String accountId = "1";
        ObjectMapper mapper = new ObjectMapper();
        UpdatePhoneNumberRequest updatePhoneNumberRequest = new UpdatePhoneNumberRequest(PHONE);
        when(accountService.update(eq(accountId), any(UpdatePhoneNumberRequest.class))).thenReturn(accountCommandCallBack);
        MvcResult mvcResult = mvc.perform(put("/accounts/{accountID}", accountId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(updatePhoneNumberRequest)))
                .andExpect(status().isOk())
                .andReturn();
        verify(accountService).update(Matchers.anyString(), Matchers.any(UpdatePhoneNumberRequest.class));
    }
}