package com.maillets.stocksimulation.dto;

public class StockSummaryDto {
	private double previousClose;
	private double open;
	private double bidPrice;
	private int bidSize;
	private double ask;
	private int askSize;
	private int volume;
	private int avgVolume;

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

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
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

	public int getAvgVolume() {
		return avgVolume;
	}

	public void setAvgVolume(int avgVolume) {
		this.avgVolume = avgVolume;
	}
}

// Prev Close: 119.72
// Open: 117.98
// Bid: 112.21 x 100
// Ask: 112.28 x 100
// 1y Target Est: 146.88
// Beta: 1.11
// Earnings Date: Oct 19 - Oct 23
// Day's Range: 113.33 - 118.18
// 52wk Range: 95.18 - 134.54
// Volume: 97,082,214
// Avg Vol (3m): 47,722,900
// Market Cap: 647.20B
// P/E (ttm): 13.13
// EPS (ttm): 8.65
// Div & Yield: 2.08 (1.70%)