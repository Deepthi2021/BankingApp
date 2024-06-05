package com.example.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Account;
import com.example.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private Account testAccount;

    @BeforeEach
    public void setUp() {
        testAccount = new Account(1L, "John Doe", 1000.0);
    }

    @Test
    public void testCreateAccount() {
        when(accountService.createAccount(any(Account.class))).thenReturn(testAccount);
        ResponseEntity<Account> responseEntity = accountController.createAccount(testAccount);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testAccount, responseEntity.getBody());
    }

    @Test
    public void testGetAccountByAccountId() {
        when(accountService.getAccountDetailsByAccountId(1L)).thenReturn(testAccount);
        Account returnedAccount = accountController.getAccountByAccountId(1L);
        assertEquals(testAccount, returnedAccount);
    }

    @Test
    public void testGetAllAccountDetails() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(testAccount);
        when(accountService.getAllAccountDetails()).thenReturn(accounts);
        List<Account> returnedAccounts = accountController.getAllAccountDetails();
        assertEquals(accounts, returnedAccounts);
    }

    @Test
    public void testDepositAccount() {
        when(accountService.depositAmount(1L, 500.0)).thenReturn(testAccount);
        Account returnedAccount = accountController.depositAccount(1L, 500.0);
        assertEquals(testAccount, returnedAccount);
    }

    @Test
    public void testWithdrawAccount() {
        when(accountService.withdrawAmount(1L, 500.0)).thenReturn(testAccount);
        Account returnedAccount = accountController.withdrawAccount(1L, 500.0);
        assertEquals(testAccount, returnedAccount);
    }

    @Test
    public void testDeleteAccount() {
        ResponseEntity<String> responseEntity = accountController.deleteAccount(1L);
        verify(accountService, times(1)).closeAccount(1L);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Account Closed", responseEntity.getBody());
    }
}
