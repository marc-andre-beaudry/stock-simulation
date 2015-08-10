package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Execution;

public interface ExecutionRepository extends JpaRepository<Execution, Integer> {

}
