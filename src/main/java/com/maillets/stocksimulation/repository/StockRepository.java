package com.maillets.stocksimulation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	  List<Stock> findBySymbol(String symbol);
}
