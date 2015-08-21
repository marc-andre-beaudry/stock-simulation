package com.maillets.stocksimulation.dto;

import com.maillets.stocksimulation.entities.Stock;

public class StockDto {

	private String symbol;
	private String exchange;
	private String name;
	private double marketCap;
	private String ipoYear;
	private String sector;
	private String industry;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}

	public String getIpoYear() {
		return ipoYear;
	}

	public void setIpoYear(String ipoYear) {
		this.ipoYear = ipoYear;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public static StockDto fromStock(Stock stock) {
		StockDto dto = new StockDto();
		dto.setSymbol(stock.getSymbol());
		dto.setExchange(stock.getExchange());
		dto.setName(stock.getName());
		dto.setSector(stock.getSector());
		dto.setIndustry(stock.getIndustry());
		dto.setIpoYear(stock.getIpoYear());
		dto.setMarketCap(stock.getMarketCap());
		return dto;
	}
}
