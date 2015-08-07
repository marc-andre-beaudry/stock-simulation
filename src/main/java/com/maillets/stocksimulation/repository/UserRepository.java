package com.maillets.stocksimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maillets.stocksimulation.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
