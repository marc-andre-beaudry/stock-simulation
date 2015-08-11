package com.maillets.stocksimulation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.Company;
import com.maillets.stocksimulation.model.MktDataProvider;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {

	@Autowired
	private MktDataProvider provider;

	@RequestMapping("")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/companies")
	public List<Company> symbols() {
		return provider.getCompanies();
	}

	@RequestMapping("/stocks/{id}")
	public String stocks() {
		return "Greetings from Spring Boot!";
	}

}
