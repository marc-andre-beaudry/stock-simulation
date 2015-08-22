package com.maillets.stocksimulation.dto;

import com.maillets.stocksimulation.entities.Execution;
import com.maillets.stocksimulation.entities.Side;

public class ExecutionDto {

	private int id;
	private String symbol;
	private int quantity;
	private Side side;
	private double price;
	private int orderId;
	private int accountId;
	private String timestamp;
	private double commission;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public static ExecutionDto fromExecution(Execution execution) {
		ExecutionDto dto = new ExecutionDto();
		dto.setCommission(execution.getCommission());
		dto.setId(execution.getId());
		dto.setOrderId(execution.getOrder().getId());
		dto.setAccountId(execution.getAccount().getId());
		dto.setPrice(execution.getPrice());
		dto.setQuantity(execution.getQuantity());
		dto.setSide(execution.getSide());
		dto.setSymbol(execution.getSymbol());
		dto.setTimestamp(execution.getTimestamp().toString());
		return dto;
	}
}
