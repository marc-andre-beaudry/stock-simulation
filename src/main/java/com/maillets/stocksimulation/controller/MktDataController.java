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
import com.maillets.stocksimulation.model.MktDataProvider;
import com.maillets.stocksimulation.repository.EodHistoricalDataRepository;
import com.maillets.stocksimulation.repository.StockRepository;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {

	@Autowired
	private MktDataProvider provider;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private EodHistoricalDataRepository eodHistoricalDataRepository;

	private final Comparator<Stock> stockComparatorBySymbol = Comparator.comparing(Stock::getSymbol);
	private final Comparator<EodHistoricalDataDto> eodDataComparatorByDate = Comparator.comparing(EodHistoricalDataDto::getDate);

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
	
	@RequestMapping("/stocks/{symbol}/profile")
	public StockProfileDto getStockProfile(@PathVariable(value = "symbol") String symbol) {
		List<Stock> stocks = stockRepository.findBySymbol(symbol);
		if (stocks.size() == 1) {
			StockProfileDto dto = new StockProfileDto();
			// TODO replace mock data
			dto.setSummary("Apple Inc. designs, manufactures, and markets mobile communication and media devices, personal computers, watches, and portable digital music players worldwide. The company also sells related software, services, accessories, networking solutions, and third-party digital content and applications. It offers iPhone, a line of smartphones that comprise a phone, music player, and Internet device; iPad, a line of multi-purpose tablets; Mac, a line of desktop and portable personal computers; iPod, a line of portable digital music and media players, such as iPod touch, iPod nano, and iPod shuffle; and Apple Watches, personal electronic devices that combine watch technology with an iOS-based user interface. The company also provides iTunes app and the iTunes Store; Mac App Store that allows customers to discover, download, and install Mac applications; iCloud, a cloud service; Apple Pay for making mobile payments; Apple TV, a portfolio of consumer and professional software applications; iOS and OS X operating systems software; iLife, a consumer-oriented digital lifestyle software application suite; iWork, an integrated productivity suite designed to help users create, present, and publish documents, presentations, and spreadsheets; and other application software, including Final Cut Pro, Logic Pro X, and its FileMaker Pro database software. In addition, it offers various Apple-branded and third-party Mac-compatible and iOS-compatible accessories, including headphones, cases, displays, storage devices, and various other connectivity and computing products and supplies. The company sells and delivers digital content and applications through the iTunes Store, App Store, iBooks Store, and Mac App Store; and sells its products through its retail stores, online stores, and direct sales force, as well as through third-party cellular network carriers, wholesalers, retailers, and value-added resellers. The company was founded in 1977 and is headquartered in Cupertino, California.");
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
		if(aggregation.equalsIgnoreCase("marketCap")) {
			List<Object[]> sectors = stockRepository.getMktCapBySector();
			for(Object[] sector: sectors) {
				SectorDto dto = new SectorDto();
				dto.setName((String)sector[0]);
				dto.setCount((double)sector[1]);
				dtos.add(dto);
			}
		} else {
			List<Object[]> sectors = stockRepository.getCountBySector();
			for(Object[] sector: sectors) {
				SectorDto dto = new SectorDto();
				dto.setName((String)sector[0]);
				dto.setCount(((BigInteger)sector[1]).intValue());
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
	@RequestMapping("/sector/{sectorName}")
	public List<IndustryDto> findIndustry(@PathVariable(value = "sectorName") String sectorName) {	
		List<IndustryDto> dtos = new ArrayList<>();
		List<Object[]> industries = stockRepository.getCountByIndustryForSector(sectorName);
		for(Object[] industry: industries) {
			IndustryDto dto = new IndustryDto();
			dto.setSector(sectorName);
			dto.setName((String)industry[0]);
			dto.setCount(((BigInteger)industry[1]).intValue());
			dtos.add(dto);
		}
		return dtos;
	}
}
