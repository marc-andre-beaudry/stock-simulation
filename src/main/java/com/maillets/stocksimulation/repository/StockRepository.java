package com.maillets.stocksimulation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maillets.stocksimulation.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	public List<Stock> findBySymbol(String symbol);

	@Query(value = "SELECT s.sector, count(*) FROM stock s GROUP BY s.sector", nativeQuery = true)
	public List<Object[]> getSectors();

	@Query(value = "SELECT s.industry, count(*) FROM stock s where s.sector = ?1 GROUP BY s.industry", nativeQuery = true)
	public List<Object[]> getIndustries(String sector);

}
