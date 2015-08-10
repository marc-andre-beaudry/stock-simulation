package com.maillets.stocksimulation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String symbol;

	@Column(nullable = false)
	private int openQuantity;

	@Column(nullable = false)
	private double averageEntryPrice;

	@Column(nullable = false)
	private double closedPnL;

	@Column(nullable = false)
	private double openPnL;

	@Column(nullable = false)
	private double totalCost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountId")
	private Account account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getOpenQuantity() {
		return openQuantity;
	}

	public void setOpenQuantity(int openQuantity) {
		this.openQuantity = openQuantity;
	}

	public double getAverageEntryPrice() {
		return averageEntryPrice;
	}

	public void setAverageEntryPrice(double averageEntryPrice) {
		this.averageEntryPrice = averageEntryPrice;
	}

	public double getClosedPnL() {
		return closedPnL;
	}

	public void setClosedPnL(double closedPnL) {
		this.closedPnL = closedPnL;
	}

	public double getOpenPnL() {
		return openPnL;
	}

	public void setOpenPnL(double openPnL) {
		this.openPnL = openPnL;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
