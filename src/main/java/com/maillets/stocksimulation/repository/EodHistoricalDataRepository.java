package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.EodHistoricalData;

public interface EodHistoricalDataRepository extends JpaRepository<EodHistoricalData, Integer> {

}
