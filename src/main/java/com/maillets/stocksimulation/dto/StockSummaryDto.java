package com.maillets.stocksimulation.dto;

public class StockSummaryDto {

	private String symbol;
	private double previousClose;
	private double open;
	private double low;
	private double high;
	private double bidPrice;
	private int bidSize;
	private double askPrice;
	private int askSize;
	private double lastPrice;
	private int lastSize;
	private double change;
	private double changePerc;
	private int volume;

	private double low52week;
	private double high52week;
	private double marketCap;
	private double eps;
	private double pe;
	private double beta;
	private int avgVolume;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getBidSize() {
		return bidSize;
	}

	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public int getAskSize() {
		return askSize;
	}

	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getLow52week() {
		return low52week;
	}

	public void setLow52week(double low52week) {
		this.low52week = low52week;
	}

	public double getHigh52week() {
		return high52week;
	}

	public void setHigh52week(double high52week) {
		this.high52week = high52week;
	}

	public double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}

	public double getEps() {
		return eps;
	}

	public void setEps(double eps) {
		this.eps = eps;
	}

	public double getPe() {
		return pe;
	}

	public void setPe(double pe) {
		this.pe = pe;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public int getAvgVolume() {
		return avgVolume;
	}

	public void setAvgVolume(int avgVolume) {
		this.avgVolume = avgVolume;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public int getLastSize() {
		return lastSize;
	}

	public void setLastSize(int lastSize) {
		this.lastSize = lastSize;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getChangePerc() {
		return changePerc;
	}

	public void setChangePerc(double changePerc) {
		this.changePerc = changePerc;
	}
}