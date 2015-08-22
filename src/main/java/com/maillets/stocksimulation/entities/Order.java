package com.maillets.stocksimulation.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_a") // "order" was causing the grammar parser of h2 to fail...
public class Order {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String symbol;

	@Column(nullable = false)
	private int totalQuantity;

	@Column(nullable = false)
	private int openQuantity;

	@Column(nullable = false)
	private int filledQuantity;

	@Column(nullable = false)
	private int canceledQuantity;

	@Column(nullable = false)
	private Side side;

	@Column(nullable = false)
	private OrderType orderType;

	@Column(nullable = false)
	private double avgExecPrice;

	@Column(nullable = false)
	private double lastExecPrice;

	@Column(nullable = false)
	private State state;

	@Column(nullable = false)
	private LocalDateTime creationTime;

	private LocalDateTime updateTime;

	@Column(nullable = false)
	private double commissionCharged;

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

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public double getCommissionCharged() {
		return commissionCharged;
	}

	public void setCommissionCharged(double commissionCharged) {
		this.commissionCharged = commissionCharged;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
