package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}
