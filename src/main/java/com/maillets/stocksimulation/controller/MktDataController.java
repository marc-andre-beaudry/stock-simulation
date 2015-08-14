package com.maillets.stocksimulation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.controller.exception.EntityNotFoundException;
import com.maillets.stocksimulation.dto.StockDto;
import com.maillets.stocksimulation.dto.StockSummaryDto;
import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.model.MktDataProvider;
import com.maillets.stocksimulation.repository.StockRepository;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {

	@Autowired
	private MktDataProvider provider;

	@Autowired
	private StockRepository stockRepository;

	private final Comparator<Stock> stockComparatorBySymbol = Comparator.comparing(Stock::getSymbol);

	@RequestMapping("/stocks")
	public List<StockDto> getStocks() {
		List<Stock> stocks = stockRepository.findAll();
		Collections.sort(stocks, stockComparatorBySymbol);
		List<StockDto> dtos = new ArrayList<>();
		for (Stock stock : stocks) {
			dtos.add(StockDto.fromStock(stock));
		}
		return dtos;
	}

	@RequestMapping("/stocks/{symbol}")
	public StockDto getStock(@PathVariable(value = "symbol") String symbol) {
		List<Stock> stocks = stockRepository.findBySymbol(symbol);
		if (stocks.size() == 1) {
			return StockDto.fromStock(stocks.get(0));
		} else {
			throw new EntityNotFoundException("[" + symbol + "] not found");
		}
	}

	@RequestMapping("/summary/{symbol}")
	public StockSummaryDto summaryForSymbol(@PathVariable(value = "symbol") String symbol) {
		StockSummaryDto dto = new StockSummaryDto();
		dto.setAskPrice(107.56);
		dto.setAskSize(1000);
		dto.setBidPrice(106.32);
		dto.setBidSize(300);
		dto.setAvgVolume(543670);
		dto.setVolume(454783);
		dto.setPreviousClose(105.05);
		dto.setOpen(106.45);
		return dto;
	}

}
