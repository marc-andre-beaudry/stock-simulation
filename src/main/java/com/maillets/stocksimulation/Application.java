package com.maillets.stocksimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.Trade;
import com.maillets.stocksimulation.entities.User;
import com.maillets.stocksimulation.repository.AccountRepository;
import com.maillets.stocksimulation.repository.OrderRepository;
import com.maillets.stocksimulation.repository.TradeRepository;
import com.maillets.stocksimulation.repository.UserRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	private AccountRepository accountRepository;
	private UserRepository userRepository;
	private TradeRepository tradeRepository;
	private OrderRepository orderRepository;
	
	@Bean
	CommandLineRunner init() {

		return arg -> {
			
			User user = new User();
			user.setFirstName("Marc-Andre");
			user.setLastName("Beaudry");
			user = userRepository.saveAndFlush(user);
			
			Account account = new Account();
			account.setName("Marc's equities account");
			account.setBalance(10000d);
			account.setOwner(user);
			account = accountRepository.saveAndFlush(account);
		
			//RestTemplate template = new RestTemplate();
			//StockQuote obj = template.getForObject("http://dev.markitondemand.com/Api/v2/Quote/json?symbol=AAPL", StockQuote.class);
			System.out.println("Init done!");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}