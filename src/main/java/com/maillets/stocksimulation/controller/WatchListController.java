package com.maillets.stocksimulation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.controller.exception.BadRequestException;
import com.maillets.stocksimulation.dto.StockDto;
import com.maillets.stocksimulation.dto.WatchListDto;
import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.entities.WatchList;
import com.maillets.stocksimulation.repository.StockRepository;
import com.maillets.stocksimulation.repository.WatchListRepository;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

	private static final Logger logger = LoggerFactory.getLogger(WatchListController.class);

	@Autowired
	private WatchListRepository watchListRepository;
	@Autowired
	private StockRepository stockRepository;

	@RequestMapping(value = "/{watchListId}", method = { RequestMethod.GET })
	public WatchListDto getWatchList(@PathVariable(value = "watchListId") String watchListId) {
		logger.debug("GET /" + watchListId);

		int parsedWatchListId = validateAndParseInt(watchListId);
		WatchList watchList = watchListRepository.findOne(parsedWatchListId);

		WatchListDto dto = new WatchListDto();
		dto.setId(watchList.getId());
		dto.setName(watchList.getName());
		dto.setUserId(watchList.getUser().getId());
		for (Stock stock : watchList.getStocks()) {
			dto.getStocks().add(StockDto.fromStock(stock));

		}
		return dto;
	}

	@RequestMapping(value = "/{watchListId}/stocks/{symbol}", method = { RequestMethod.PUT })
	public void addStockToWatchList(@PathVariable(value = "watchListId") String watchListId, @PathVariable(value = "symbol") String symbol) {
		logger.debug("PUT /" + watchListId + "/stocks/" + symbol);

		int parsedWatchListId = validateAndParseInt(watchListId);
		WatchList watchList = watchListRepository.findOne(parsedWatchListId);
		boolean anyExisting = watchList.getStocks().stream().anyMatch(x -> x.getSymbol().equalsIgnoreCase(symbol));
		if (!anyExisting) {
			Stock stock = stockRepository.findBySymbol(symbol).get(0);
			watchList.getStocks().add(stock);
			watchListRepository.saveAndFlush(watchList);
		}
	}

	@RequestMapping(value = "/{watchListId}/stocks/{symbol}", method = { RequestMethod.DELETE })
	public void removeStockFromWatchList(@PathVariable(value = "watchListId") String watchListId, @PathVariable(value = "symbol") String symbol) {
		logger.debug("DELETE /" + watchListId + "/stocks/" + symbol);

		int parsedWatchListId = validateAndParseInt(watchListId);
		WatchList watchList = watchListRepository.findOne(parsedWatchListId);
		boolean anyExisting = watchList.getStocks().stream().anyMatch(x -> x.getSymbol().equalsIgnoreCase(symbol));
		if (anyExisting) {
			watchList.getStocks().removeIf(x -> x.getSymbol().equalsIgnoreCase(symbol));
			watchListRepository.saveAndFlush(watchList);
		}
	}

	private int validateAndParseInt(String id) {
		try {
			int parsedInt = Integer.parseInt(id);
			return parsedInt;
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}
	}

}
