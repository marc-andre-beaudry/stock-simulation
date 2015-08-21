package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.StockProfile;

public interface StockProfileRepository extends JpaRepository<StockProfile, Integer> {

}
