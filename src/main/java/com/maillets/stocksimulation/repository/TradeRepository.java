package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
