package com.maillets.stocksimulation.dto;

import com.maillets.stocksimulation.entities.Position;

public class PositionDto {

	private int id;
	private String symbol;
	private int openQuantity;
	private double currentMarketValue;
	private double currentPrice;
	private double averageEntryPrice;
	private double closedPnL;
	private double openPnL;
	private double totalCost;
	private int accountId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getCurrentMarketValue() {
		return currentMarketValue;
	}

	public void setCurrentMarketValue(double currentMarketValue) {
		this.currentMarketValue = currentMarketValue;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public static PositionDto fromPosition(Position position) {
		PositionDto dto = new PositionDto();
		dto.setId(position.getId());
		dto.setAverageEntryPrice(position.getAverageEntryPrice());
		dto.setClosedPnL(position.getClosedPnL());
		dto.setOpenPnL(position.getOpenPnL());
		dto.setOpenQuantity(position.getOpenQuantity());
		dto.setSymbol(position.getSymbol());
		dto.setTotalCost(position.getTotalCost());
		dto.setAccountId(position.getAccount().getId());
		return dto;
	}
}
