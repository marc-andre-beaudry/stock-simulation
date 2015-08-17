package com.maillets.stocksimulation.dto;

import java.time.format.DateTimeFormatter;

import com.maillets.stocksimulation.entities.EodHistoricalData;

public class EodHistoricalDataDto {

	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

	public static EodHistoricalDataDto fromEodHistoricalData(EodHistoricalData data) {
		EodHistoricalDataDto dto = new EodHistoricalDataDto();
		dto.setDate(data.getDate().format(DateTimeFormatter.BASIC_ISO_DATE));
		dto.setOpen(data.getOpen());
		dto.setHigh(data.getHigh());
		dto.setLow(data.getLow());
		dto.setClose(data.getClose());
		dto.setVolume(data.getVolume());
		dto.setAdjClose(data.getAdjClose());
		return dto;
	}
}
