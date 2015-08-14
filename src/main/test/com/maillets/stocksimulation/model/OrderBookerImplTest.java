package com.maillets.stocksimulation.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maillets.stocksimulation.TestApplication;
import com.maillets.stocksimulation.dto.OrderDto;
import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.Order;
import com.maillets.stocksimulation.entities.OrderType;
import com.maillets.stocksimulation.entities.Side;
import com.maillets.stocksimulation.entities.State;
import com.maillets.stocksimulation.entities.User;
import com.maillets.stocksimulation.external.StockQuote;
import com.maillets.stocksimulation.repository.AccountRepository;
import com.maillets.stocksimulation.repository.ExecutionRepository;
import com.maillets.stocksimulation.repository.OrderRepository;
import com.maillets.stocksimulation.repository.PositionRepository;
import com.maillets.stocksimulation.repository.UserRepository;

@ContextConfiguration(classes = { TestApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderBookerImplTest {

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

	@Mock
	private MktDataProvider mktDataProvider;
	@Mock
	private CommissionModel commissionModel;

	private OrderBooker orderBooker;
	private Account account;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(commissionModel.calculateCommision(Matchers.anyInt())).thenReturn(0D);
		accountRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();

		User user = new User();
		user.setFirstName("Marc-Andre");
		user.setLastName("Beaudry");
		user = userRepository.saveAndFlush(user);

		account = new Account();
		account.setName("Marc's equities account");
		account.setBalance(10000d);
		account.setOwner(user);
		account = accountRepository.saveAndFlush(account);
		orderBooker = new OrderBookerImpl(orderRepository, executionRepository, positionRepository, mktDataProvider, commissionModel);
	}

	@Test
	public void test() {
		StockQuote quote = new StockQuote();
		quote.setLastPrice(100D);
		when(mktDataProvider.getStockQuote(Matchers.anyString())).thenReturn(quote);
		assertEquals(100D, quote.getLastPrice(), 0.01);

		OrderDto orderDto = new OrderDto();
		orderDto.setSide(Side.Buy);
		orderDto.setOrderType(OrderType.Market);
		orderDto.setSymbol("AAPL");
		orderDto.setOpenQuantity(100);
		Order order = orderBooker.bookOrder(account, orderDto);
		assertEquals(State.Executed, order.getState());
		assertEquals(true, true);
	}
}
