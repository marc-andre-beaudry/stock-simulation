package com.maillets.stocksimulation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.dto.StockDto;
import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.model.MktDataProvider;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {

	@Autowired
	private MktDataProvider provider;

	private final Comparator<Stock> stockComparatorBySymbol = Comparator.comparing(Stock::getSymbol);

	@RequestMapping("")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/stocks")
	public List<StockDto> symbols() {
		List<Stock> stocks = provider.getStocks();
		Collections.sort(stocks, stockComparatorBySymbol);
		List<StockDto> dtos = new ArrayList<>();
		for (Stock stock : stocks) {
			dtos.add(StockDto.fromStock(stock));
		}
		return dtos;
	}

	@RequestMapping("/stocks/{id}")
	public String stocks() {
		return "Greetings from Spring Boot!";
	}

}
