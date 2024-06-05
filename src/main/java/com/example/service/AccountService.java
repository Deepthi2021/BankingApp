package com.example.service;

import java.util.List;

import com.example.entity.Account;

public interface AccountService {
	public Account createAccount(Account account);
	public Account getAccountDetailsByAccountId(long accountid);
	public List<Account>getAllAccountDetails();
	public Account depositAmount(long accountid,double amount);
	public Account withdrawAmount(long accountid,double amount);
	public void closeAccount(long accountid);
}
