package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService service;
	//create account
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		Account createAccount = service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
	}
	@GetMapping("/{accountId}")
	public Account getAccountByAccountId(@PathVariable Long accountId) {
		Account account = service.getAccountDetailsByAccountId(accountId);
		return account;
	}
	@GetMapping("/{getallaccounts}")
	public List<Account> getAllAccountDetails(){
		List<Account> allAccountDetails = service.getAllAccountDetails();
		return allAccountDetails;
	}
	@PutMapping("/deposit/{accountId}/{amount}")
	public Account depositAccount(@PathVariable long accountId, @PathVariable double amount) {
		Account account = service.depositAmount(accountId, amount);
		return account;
	}
	@PutMapping("/withdraw/{accountid}/{amount}")
	public Account withdrawAccount(@PathVariable long accountId,@PathVariable double amount ) {
		Account account = service.withdrawAmount(accountId, amount);
		return account;
	}
	@DeleteMapping("/delete/{accountId}")
		public ResponseEntity<String> deleteAccount(@PathVariable long accountId) 
		{
			service.closeAccount(accountId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account Closed");
		}
	}
