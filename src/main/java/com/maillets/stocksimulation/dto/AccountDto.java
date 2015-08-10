package com.maillets.stocksimulation.dto;

import com.maillets.stocksimulation.entities.Account;

public class AccountDto {

	private int id;
	private String name;
	private double balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static AccountDto fromAccount(Account account) {
		AccountDto dto = new AccountDto();
		dto.setId(account.getId());
		dto.setBalance(account.getBalance());
		dto.setName(account.getName());
		return dto;
	}
}
