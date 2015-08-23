package com.maillets.stocksimulation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.controller.exception.BadRequestException;
import com.maillets.stocksimulation.controller.exception.EntityNotFoundException;
import com.maillets.stocksimulation.dto.AccountDto;
import com.maillets.stocksimulation.dto.ExecutionDto;
import com.maillets.stocksimulation.dto.OrderDto;
import com.maillets.stocksimulation.dto.PositionDto;
import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.Execution;
import com.maillets.stocksimulation.entities.Order;
import com.maillets.stocksimulation.entities.Position;
import com.maillets.stocksimulation.model.OrderBooker;
import com.maillets.stocksimulation.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OrderBooker orderBooker;

	private final Comparator<Position> positionComparatorById = Comparator.comparing(Position::getId);
	private final Comparator<Order> orderComparatorById = Comparator.comparing(Order::getId);
	private final Comparator<Execution> executionComparatorById = Comparator.comparing(Execution::getId);

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public List<AccountDto> getAccounts() {
		logger.debug("GET /");

		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> dtos = new ArrayList<>();
		for (Account account : accounts) {
			dtos.add(AccountDto.fromAccount(account));
		}
		return dtos;
	}

	@RequestMapping(value = "/{accountId}", method = { RequestMethod.GET })
	public AccountDto getAccount(@PathVariable(value = "accountId") String accountId) {
		logger.debug("GET /" + accountId);

		Account account = validateAndGetPlayer(accountId);
		return AccountDto.fromAccount(account);
	}

	@RequestMapping(value = "/{accountId}/positions", method = { RequestMethod.GET })
	public List<PositionDto> getAccountPositions(@PathVariable(value = "accountId") String accountId) {
		logger.debug("GET /" + accountId + "/positions");

		Account account = validateAndGetPlayer(accountId);
		List<Position> positions = new ArrayList<Position>(account.getPositions());
		Collections.sort(positions, positionComparatorById);
		List<PositionDto> positionDtos = new ArrayList<>();
		for (Position position : positions) {
			positionDtos.add(PositionDto.fromPosition(position));
		}
		return positionDtos;
	}

	@RequestMapping(value = "/{accountId}/orders", method = { RequestMethod.GET })
	public List<OrderDto> getAccountOrders(@PathVariable(value = "accountId") String accountId) {
		logger.debug("GET /" + accountId + "/orders");

		Account account = validateAndGetPlayer(accountId);
		List<Order> orders = new ArrayList<Order>(account.getOrders());
		Collections.sort(orders, orderComparatorById);
		List<OrderDto> orderDtos = new ArrayList<>();
		for (Order order : orders.stream().sorted((e1, e2) -> -e1.getCreationTime().compareTo(e2.getCreationTime())).collect(Collectors.toList())) {
			orderDtos.add(OrderDto.fromOrder(order));
		}
		return orderDtos;
	}

	@RequestMapping(value = "/{accountId}/orders/{orderId}", method = { RequestMethod.GET })
	public OrderDto getAccountOrder(@PathVariable(value = "accountId") String accountId,
			@PathVariable(value = "orderId") String orderId) {
		logger.debug("GET /" + accountId + "/orders/" + orderId);

		Account account = validateAndGetPlayer(accountId);
		int parsedOrderId = validateAndParseInt(orderId);
		Optional<Order> order = account.getOrders().stream().filter(x -> x.getId() == parsedOrderId).findFirst();
		if (order.isPresent()) {
			return OrderDto.fromOrder(order.get());
		} else {
			throw new EntityNotFoundException("OrderId : " + orderId);
		}
	}

	@RequestMapping(value = "/{accountId}/executions", method = { RequestMethod.GET })
	public List<ExecutionDto> getAccountExecutions(@PathVariable(value = "accountId") String accountId) {
		logger.debug("GET /" + accountId + "/executions");

		Account account = validateAndGetPlayer(accountId);
		List<Execution> executions = new ArrayList<Execution>(account.getExecutions());
		Collections.sort(executions, executionComparatorById);
		List<ExecutionDto> executionsDtos = new ArrayList<>();
		for (Execution execution : executions.stream().sorted((e1, e2) -> -e1.getTimestamp().compareTo(e2.getTimestamp())).collect(Collectors.toList())) {
			executionsDtos.add(ExecutionDto.fromExecution(execution));
		}
		return executionsDtos;
	}

	@RequestMapping(value = "/{accountId}/orders", method = { RequestMethod.POST })
	public OrderDto addOrUpdateOrder(@PathVariable(value = "accountId") String accountId, @RequestBody OrderDto dto) {
		logger.debug("POST /" + accountId + "/orders");

		Account account = validateAndGetPlayer(accountId);
		if (dto.getId() == null) {
			Order order = orderBooker.bookOrder(account, dto);
			return OrderDto.fromOrder(order);
		} else {
			Order order = orderBooker.modifyOrder(account, dto);
			return OrderDto.fromOrder(order);
		}
	}

	@RequestMapping(value = "/{accountId}/orders/{orderId}", method = { RequestMethod.DELETE })
	public void deleteOrder(@PathVariable(value = "accountId") String accountId,
			@PathVariable(value = "orderId") String orderId) {
		logger.debug("DELETE /" + accountId + "/orders/" + orderId);

		// Account account = validateAndGetPlayer(accountId);
		throw new UnsupportedOperationException();
	}

	private Account validateAndGetPlayer(String id) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}

		Account account = accountRepository.findOne(parsedInt);
		if (account == null) {
			throw new EntityNotFoundException(id);
		}
		return account;
	}

	private int validateAndParseInt(String id) {
		try {
			int parsedInt = Integer.parseInt(id);
			return parsedInt;
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}
	}

	// private Order validateAndGetAccountOrder(Account account, String id) {
	// Optional<Order> order;
	// try {
	// final int parsedInt = Integer.parseInt(id);
	// order = account.getOrders().stream().filter(x -> x.getId() ==
	// parsedInt).findFirst();
	// } catch (NumberFormatException e) {
	// throw new BadRequestException();
	// }
	//
	// if (!order.isPresent()) {
	// throw new EntityNotFoundException(id);
	// }
	// return order.get();
	// }
}
