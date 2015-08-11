package com.maillets.stocksimulation.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.external.StockQuote;
import com.maillets.stocksimulation.repository.StockRepository;

public class MktDataProviderImpl implements MktDataProvider {

	@Autowired
	private StockRepository stockRepository;

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

	@Override
	public List<Stock> getStocks() {
		return stockRepository.findAll();
	}

}
