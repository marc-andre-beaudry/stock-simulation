package com.maillets.stocksimulation.external;


public interface MktDataProvider {
	StockQuote getStockQuote(String symbol);
}
