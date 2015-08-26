package com.maillets.stocksimulation.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.controller.exception.EntityNotFoundException;
import com.maillets.stocksimulation.dto.EodHistoricalDataDto;
import com.maillets.stocksimulation.dto.IndustryDto;
import com.maillets.stocksimulation.dto.MoverDto;
import com.maillets.stocksimulation.dto.MoverType;
import com.maillets.stocksimulation.dto.SectorDto;
import com.maillets.stocksimulation.dto.StockDto;
import com.maillets.stocksimulation.dto.StockProfileDto;
import com.maillets.stocksimulation.dto.StockSummaryDto;
import com.maillets.stocksimulation.entities.EodHistoricalData;
import com.maillets.stocksimulation.entities.Stock;
import com.maillets.stocksimulation.entities.StockProfile;
import com.maillets.stocksimulation.repository.EodHistoricalDataRepository;
import com.maillets.stocksimulation.repository.StockRepository;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private EodHistoricalDataRepository eodHistoricalDataRepository;

	private final Comparator<Stock> stockComparatorBySymbol = Comparator.comparing(Stock::getSymbol);
	private final Comparator<EodHistoricalDataDto> eodDataComparatorByDate = Comparator
			.comparing(EodHistoricalDataDto::getDate);

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

	@RequestMapping("/stocks/{symbol}/competitors")
	public List<StockDto> getCompetitors(@PathVariable(value = "symbol") String symbol) {
		List<Stock> stocks = stockRepository.findBySymbol(symbol);
		List<StockDto> dtos = new ArrayList<>();
		if (stocks.size() == 1) {
			Stock stock = stocks.get(0);
			List<Stock> competitors = stockRepository.findByIndustry(stock.getIndustry());
			for (Stock competitor : competitors) {
				dtos.add(StockDto.fromStock(competitor));
			}
			return dtos;
		} else {
			throw new EntityNotFoundException("[" + symbol + "] not found");
		}
	}

	@RequestMapping("/stocks/{symbol}/profile")
	public StockProfileDto getStockProfile(@PathVariable(value = "symbol") String symbol) {
		List<Stock> stocks = stockRepository.findBySymbol(symbol);
		if (stocks.size() == 1) {
			StockProfileDto dto = new StockProfileDto();
			StockProfile profile = stocks.get(0).getStockProfile();
			if (profile != null && profile.getSummary() != null && !profile.getSummary().isEmpty()) {
				dto.setSummary(profile.getSummary());
			} else {
				dto.setSummary("No summary available");
			}
			return dto;
		} else {
			throw new EntityNotFoundException("[" + symbol + "] not found");
		}
	}

	@RequestMapping("/eod/{symbol}")
	public List<EodHistoricalDataDto> getEodHistoricalData(@PathVariable(value = "symbol") String symbol) {
		List<EodHistoricalData> dataList = eodHistoricalDataRepository.findAll();
		List<EodHistoricalDataDto> dtos = new ArrayList<>();
		for (EodHistoricalData data : dataList) {
			dtos.add(EodHistoricalDataDto.fromEodHistoricalData(data));
		}
		Collections.sort(dtos, eodDataComparatorByDate);
		return dtos;
	}

	@RequestMapping("/summary/{symbol}")
	public StockSummaryDto summaryForSymbol(@PathVariable(value = "symbol") String symbol) {
		StockSummaryDto dto = new StockSummaryDto();
		dto.setSymbol(symbol);
		dto.setAskPrice(107.56);
		dto.setAskSize(1000);
		dto.setBidPrice(106.32);
		dto.setBidSize(300);
		dto.setAvgVolume(543670);
		dto.setVolume(454783);
		dto.setPreviousClose(105.05);
		dto.setOpen(106.45);
		dto.setLastPrice(106.50);
		dto.setLastSize(200);
		dto.setChangePerc(2.56);
		dto.setChange(0.56);
		return dto;
	}

	@RequestMapping("/mover/{moverType}")
	public List<MoverDto> getMovers(@PathVariable(value = "moverType") MoverType moverType) {
		List<MoverDto> dtos = new ArrayList<>();

		if (moverType == MoverType.MktCapLoser || moverType == MoverType.PriceLoser) {
			PageRequest page = new PageRequest(1, 5, Direction.DESC, "marketCap");
			Page<Stock> stocks = stockRepository.findAll(page);
			for (Stock stock : stocks) {
				MoverDto dto = new MoverDto();
				dto.setSymbol(stock.getSymbol());
				dto.setName(stock.getName());
				dto.setMarketCap(stock.getMarketCap());
				dto.setChange(-Math.random() * 5.0 - 1.0);
				dtos.add(dto);
			}
		} else {
			PageRequest page = new PageRequest(0, 5, Direction.DESC, "marketCap");
			Page<Stock> stocks = stockRepository.findAll(page);
			for (Stock stock : stocks) {
				MoverDto dto = new MoverDto();
				dto.setSymbol(stock.getSymbol());
				dto.setName(stock.getName());
				dto.setMarketCap(stock.getMarketCap());
				dto.setChange(Math.random() * 5.0 + 1.0);
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@RequestMapping("/sector")
	public List<SectorDto> findSector(@RequestParam(value = "aggregation") String aggregation) {
		List<SectorDto> dtos = new ArrayList<>();
		if (aggregation.equalsIgnoreCase("marketCap")) {
			List<Object[]> sectors = stockRepository.getMktCapBySector();
			for (Object[] sector : sectors) {
				SectorDto dto = new SectorDto();
				dto.setName((String) sector[0]);
				dto.setCount((double) sector[1]);
				dtos.add(dto);
			}
		} else {
			List<Object[]> sectors = stockRepository.getCountBySector();
			for (Object[] sector : sectors) {
				SectorDto dto = new SectorDto();
				dto.setName((String) sector[0]);
				dto.setCount(((BigInteger) sector[1]).intValue());
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@RequestMapping("/sector/{sectorName}")
	public List<IndustryDto> findIndustry(@PathVariable(value = "sectorName") String sectorName) {
		List<IndustryDto> dtos = new ArrayList<>();
		List<Object[]> industries = stockRepository.getCountByIndustryForSector(sectorName);
		for (Object[] industry : industries) {
			IndustryDto dto = new IndustryDto();
			dto.setSector(sectorName);
			dto.setName((String) industry[0]);
			dto.setCount(((BigInteger) industry[1]).intValue());
			dtos.add(dto);
		}
		return dtos;
	}
}
