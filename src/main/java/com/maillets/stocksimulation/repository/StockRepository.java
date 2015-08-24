package com.maillets.stocksimulation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maillets.stocksimulation.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	public List<Stock> findBySymbol(String symbol);

	@Query(value = "SELECT s.sector, count(*) FROM stock s GROUP BY s.sector", nativeQuery = true)
	public List<Object[]> getCountBySector();
	
	@Query(value = "SELECT sector, sum(marketCap) as totalMarketCap FROM stock s GROUP BY sector", nativeQuery = true)
	public List<Object[]> getMktCapBySector();

	@Query(value = "SELECT s.industry, count(*) FROM stock s where s.sector = ?1 GROUP BY s.industry", nativeQuery = true)
	public List<Object[]> getCountByIndustryForSector(String sector);

	public List<Stock> findBySector(String sector);

	public List<Stock> findByIndustry(String industry);
}
