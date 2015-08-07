package com.maillets.stocksimulation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mktdata")
public class MktDataController {
    
    @RequestMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/symbols")
    public List<String> symbols() {
    	List<String> symbols = new ArrayList<>();
    	symbols.add("AAPL");
    	symbols.add("MSFT");
    	symbols.add("BBRY");
    	symbols.add("MS");
    	symbols.add("FB");
    	
    	return symbols;
    }
    
    @RequestMapping("/stocks/{id}")
    public String stocks() {
        return "Greetings from Spring Boot!";
    }
    
}
