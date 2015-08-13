package com.maillets.stocksimulation.dto;

import java.util.ArrayList;
import java.util.List;

public class WatchListDto {
	
	private int id;
	private String name;
	private int userId;
	private List<StockDto> stocks = new ArrayList<>();

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<StockDto> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockDto> stocks) {
		this.stocks = stocks;
	}
}
