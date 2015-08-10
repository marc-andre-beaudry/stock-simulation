package com.maillets.stocksimulation.dto;

import java.time.LocalTime;

import com.maillets.stocksimulation.entities.Order;
import com.maillets.stocksimulation.entities.OrderType;
import com.maillets.stocksimulation.entities.Side;
import com.maillets.stocksimulation.entities.State;

public class OrderDto {

	private Integer id;
	private String symbol;
	private int totalQuantity;
	private int openQuantity;
	private int filledQuantity;
	private int canceledQuantity;
	private Side side;
	private OrderType orderType;
	private double avgExecPrice;
	private double lastExecPrice;
	private State state;
	private LocalTime creationTime;
	private LocalTime updateTime;
	private double commissionCharged;

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

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getOpenQuantity() {
		return openQuantity;
	}

	public void setOpenQuantity(int openQuantity) {
		this.openQuantity = openQuantity;
	}

	public int getFilledQuantity() {
		return filledQuantity;
	}

	public void setFilledQuantity(int filledQuantity) {
		this.filledQuantity = filledQuantity;
	}

	public int getCanceledQuantity() {
		return canceledQuantity;
	}

	public void setCanceledQuantity(int canceledQuantity) {
		this.canceledQuantity = canceledQuantity;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public double getAvgExecPrice() {
		return avgExecPrice;
	}

	public void setAvgExecPrice(double avgExecPrice) {
		this.avgExecPrice = avgExecPrice;
	}

	public double getLastExecPrice() {
		return lastExecPrice;
	}

	public void setLastExecPrice(double lastExecPrice) {
		this.lastExecPrice = lastExecPrice;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LocalTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalTime updateTime) {
		this.updateTime = updateTime;
	}

	public double getCommissionCharged() {
		return commissionCharged;
	}

	public void setCommissionCharged(double commissionCharged) {
		this.commissionCharged = commissionCharged;
	}

	public static OrderDto fromOrder(Order order) {
		OrderDto dto = new OrderDto();
		dto.setAvgExecPrice(order.getAvgExecPrice());
		dto.setCanceledQuantity(order.getCanceledQuantity());
		dto.setCommissionCharged(order.getCommissionCharged());
		dto.setCreationTime(order.getCreationTime());
		dto.setFilledQuantity(order.getFilledQuantity());
		dto.setId(order.getId());
		dto.setLastExecPrice(order.getLastExecPrice());
		dto.setOpenQuantity(order.getOpenQuantity());
		dto.setOrderType(order.getOrderType());
		dto.setSide(order.getSide());
		dto.setState(order.getState());
		dto.setSymbol(order.getSymbol());
		dto.setTotalQuantity(order.getTotalQuantity());
		dto.setUpdateTime(order.getUpdateTime());
		return null;
	}
}
