package com.maillets.stocksimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping("")
	public void getAccounts() {
		
	}

	@RequestMapping("/{id}")
	public void getAccount() {
	}
	
	@RequestMapping("/{id}/positions")
	public void getAccountPositions() {
	}
	
	@RequestMapping("/{id}/orders")
	public void getAccountOrders() {
	}
	
	@RequestMapping("/{id}/trades")
	public void getAccountTrades() {
	}
}
