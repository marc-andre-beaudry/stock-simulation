package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
