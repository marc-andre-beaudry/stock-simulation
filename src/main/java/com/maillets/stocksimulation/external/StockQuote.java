package com.maillets.stocksimulation.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {

	@JsonProperty("Name")
	private String name;
	@JsonProperty("Symbol")
	private String symbol;

	@JsonProperty("Open")
	private Double open;
	@JsonProperty("High")
	private Double high;
	@JsonProperty("Low")
	private Double low;
	@JsonProperty("LastPrice")
	private Double lastPrice;

	@JsonProperty("Change")
	private Double change;
	@JsonProperty("ChangePercent")
	private Double changePercent;

	@JsonProperty("ChangeYTD")
	private Double changeYTD;
	@JsonProperty("ChangePercentYTD")
	private Double changePercentYTD;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}

	public Double getChangeYTD() {
		return changeYTD;
	}

	public void setChangeYTD(Double changeYTD) {
		this.changeYTD = changeYTD;
	}

	public Double getChangePercentYTD() {
		return changePercentYTD;
	}

	public void setChangePercentYTD(Double changePercentYTD) {
		this.changePercentYTD = changePercentYTD;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}

	private Long volume;
	private Long marketCap;
}
