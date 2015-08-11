package com.maillets.stocksimulation.model;

import java.util.List;

import com.maillets.stocksimulation.Company;
import com.maillets.stocksimulation.CompanySeedLoader;
import com.maillets.stocksimulation.external.StockQuote;

public class MktDataProviderImpl implements MktDataProvider {

	private final List<Company> companies;

	public StockQuote getStockQuote(String symbol) {
		StockQuote quote = new StockQuote();
		quote.setSymbol(symbol);
		quote.setName(symbol);
		quote.setChange(0D);
		quote.setChangePercent(0D);
		quote.setChangePercentYTD(0D);
		quote.setChangeYTD(0D);
		quote.setOpen(105D);
		quote.setHigh(110D);
		quote.setLow(100D);
		quote.setMarketCap(150000000L);
		quote.setVolume(10000L);
		quote.setLastPrice(Math.random() * 10 + 100);
		return quote;
	}

	public MktDataProviderImpl() {
		companies = CompanySeedLoader.load("company_seed.data");
	}

	@Override
	public List<Company> getCompanies() {
		return companies;
	}

}
