package com.maillets.stocksimulation.model;

import java.util.List;

import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.external.StockQuote;

public interface MktDataProvider {
	StockQuote getStockQuote(String symbol);

	List<Stock> getStocks();
}
