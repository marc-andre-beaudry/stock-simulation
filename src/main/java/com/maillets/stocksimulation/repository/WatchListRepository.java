package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {

}
