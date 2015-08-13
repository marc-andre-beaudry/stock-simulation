package com.maillets.stocksimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.controller.exception.BadRequestException;
import com.maillets.stocksimulation.dto.StockDto;
import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.entities.WatchList;
import com.maillets.stocksimulation.entities.WatchListDto;
import com.maillets.stocksimulation.repository.WatchListRepository;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

	@Autowired
	private WatchListRepository watchListRepository;

	@RequestMapping(value = "/{watchListId}", method = { RequestMethod.GET })
	public WatchListDto getWatchList(@PathVariable(value = "watchListId") String watchListId) {

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
	
	@RequestMapping(value = "/{watchListId}/stocks", method = { RequestMethod.POST })
	public void addStockToWatchList(@PathVariable(value = "watchListId") String watchListId) {
		
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
