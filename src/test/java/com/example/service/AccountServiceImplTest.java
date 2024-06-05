package com.example.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.Account;
import com.example.repo.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account testAccount;

    @BeforeEach
    public void setUp() {
        testAccount = new Account(1L, "John Doe", 1000.0);
    }

    @Test
    public void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
        Account createdAccount = accountService.createAccount(testAccount);
        assertNotNull(createdAccount);
        assertEquals(testAccount, createdAccount);
    }

    @Test
    public void testGetAccountDetailsByAccountId() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));
        Account returnedAccount = accountService.getAccountDetailsByAccountId(1L);
        assertEquals(testAccount, returnedAccount);
    }

    @Test
    public void testWithdrawAmount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));
        double withdrawAmount = 500.0;
        double expectedBalance = testAccount.getAccount_balance() - withdrawAmount;
        Account updatedAccount = accountService.withdrawAmount(1L, withdrawAmount);
        assertNotNull(updatedAccount);
        assertEquals(expectedBalance, updatedAccount.getAccount_balance());
    }

    @Test
    public void testCloseAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount));
        accountService.closeAccount(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCloseAccount_NonExistentAccount() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> accountService.closeAccount(2L));
    }
}
