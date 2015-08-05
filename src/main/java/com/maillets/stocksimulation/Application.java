package com.maillets.stocksimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init() {

		return arg -> {
			System.out.println("Init done!");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}