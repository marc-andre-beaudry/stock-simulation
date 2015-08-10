package com.maillets.stocksimulation.external;


public interface MktDataProviderExt {
	StockQuote getStockQuote(String symbol);
}
