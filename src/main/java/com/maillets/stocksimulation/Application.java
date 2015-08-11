package com.maillets.stocksimulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maillets.stocksimulation.dto.OrderDto;
import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.OrderType;
import com.maillets.stocksimulation.entities.Side;
import com.maillets.stocksimulation.entities.User;
import com.maillets.stocksimulation.model.CommissionModel;
import com.maillets.stocksimulation.model.CommissionModelImpl;
import com.maillets.stocksimulation.model.MktDataProvider;
import com.maillets.stocksimulation.model.MktDataProviderImpl;
import com.maillets.stocksimulation.model.OrderBooker;
import com.maillets.stocksimulation.model.OrderBookerImpl;
import com.maillets.stocksimulation.repository.AccountRepository;
import com.maillets.stocksimulation.repository.ExecutionRepository;
import com.maillets.stocksimulation.repository.OrderRepository;
import com.maillets.stocksimulation.repository.PositionRepository;
import com.maillets.stocksimulation.repository.UserRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ExecutionRepository executionRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private OrderBooker orderBooker;

	@Bean
	public MktDataProvider mktDataProvider() {
		return new MktDataProviderImpl();
	}

	@Bean
	public CommissionModel commissionModel() {
		return new CommissionModelImpl();
	}

	@Bean
	public OrderBooker orderBooker() {
		return new OrderBookerImpl(accountRepository, orderRepository, executionRepository, positionRepository, mktDataProvider(), commissionModel());
	}

	@Bean
	CommandLineRunner init() {

		return arg -> {

			try {

				User user = new User();
				user.setFirstName("Marc-Andre");
				user.setLastName("Beaudry");
				user = userRepository.saveAndFlush(user);

				Account account = new Account();
				account.setName("Marc's equities account");
				account.setBalance(10000d);
				account.setOwner(user);
				account = accountRepository.saveAndFlush(account);

				OrderDto dto = new OrderDto();
				dto.setSide(Side.Buy);
				dto.setOrderType(OrderType.Market);
				dto.setOpenQuantity(100);
				dto.setSymbol("AAPL");
				orderBooker.bookOrder(account, dto);

				OrderDto dto2 = new OrderDto();
				dto2.setSide(Side.Buy);
				dto2.setOrderType(OrderType.Market);
				dto2.setOpenQuantity(200);
				dto2.setSymbol("AAPL");
				orderBooker.bookOrder(account, dto2);

				OrderDto dto3 = new OrderDto();
				dto3.setSide(Side.Buy);
				dto3.setOrderType(OrderType.Market);
				dto3.setOpenQuantity(500);
				dto3.setSymbol("AAPL");
				orderBooker.bookOrder(account, dto3);
				// RestTemplate template = new RestTemplate();
				// StockQuote obj =
				// template.getForObject("http://dev.markitondemand.com/Api/v2/Quote/json?symbol=AAPL",
				// StockQuote.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Init done!");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}