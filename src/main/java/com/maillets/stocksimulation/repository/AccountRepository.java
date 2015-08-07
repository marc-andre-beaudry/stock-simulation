package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
