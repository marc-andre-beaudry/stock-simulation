package com.maillets.stocksimulation;

public class Company {
	private String symbol;
	private String name;
	private double lastSale;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLastSale() {
		return lastSale;
	}

	public void setLastSale(double lastSale) {
		this.lastSale = lastSale;
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
}
