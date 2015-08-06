package com.maillets.stocksimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.maillets.stocksimulation.external.StockQuote;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init() {

		return arg -> {

			//RestTemplate template = new RestTemplate();
			//StockQuote obj = template.getForObject("http://dev.markitondemand.com/Api/v2/Quote/json?symbol=AAPL", StockQuote.class);
			System.out.println("Init done!");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}