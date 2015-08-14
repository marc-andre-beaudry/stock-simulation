package com.maillets.stocksimulation.model;

import com.maillets.stocksimulation.external.StockQuote;

public interface MktDataProvider {
	StockQuote getStockQuote(String symbol);
}
