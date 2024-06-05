package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repo.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository repo;
	
	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountId(long accountid) {
		Optional <Account> account =  repo.findById(accountid);
		if(account.isEmpty()) {
			throw new RuntimeException("Account does not exist");
		}
		Account account_found =account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> listOfAccounts = repo.findAll();
		return null;
	}

	@Override
	public Account depositAmount(long accountid, double amount) {
		Optional<Account> account = repo.findById(accountid);
		if(account.isEmpty()) {
			throw new RuntimeException("Account does not exist");
		}
		Account accountPresent = account.get();		
		double totalBalance=accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		return null;
	}

	@Override
	public Account withdrawAmount(long accountid, double amount) {
		Optional<Account> account = repo.findById(accountid);
		if(account.isEmpty()) {
			throw new RuntimeException("Account does not exist");
		}
		Account accountPresent = account.get();
		double accountBalance = accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public void closeAccount(long accountid) {
		getAccountDetailsByAccountId(accountid);
		repo.deleteById(accountid);
		
	}

}
