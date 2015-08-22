package com.maillets.stocksimulation.model;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.maillets.stocksimulation.dto.OrderDto;
import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.Execution;
import com.maillets.stocksimulation.entities.Order;
import com.maillets.stocksimulation.entities.Position;
import com.maillets.stocksimulation.entities.Side;
import com.maillets.stocksimulation.entities.State;
import com.maillets.stocksimulation.external.StockQuote;
import com.maillets.stocksimulation.repository.ExecutionRepository;
import com.maillets.stocksimulation.repository.OrderRepository;
import com.maillets.stocksimulation.repository.PositionRepository;

public class OrderBookerImpl implements OrderBooker {

	private final OrderRepository orderRepository;
	private final ExecutionRepository executionRepository;
	private final PositionRepository positionRepository;
	private final MktDataProvider mktDataProvider;
	private final CommissionModel commissionModel;

	@Autowired
	public OrderBookerImpl(OrderRepository orderRepository, ExecutionRepository executionRepository,
			PositionRepository positionRepository, MktDataProvider mktDataProvider, CommissionModel commissionModel) {
		this.orderRepository = orderRepository;
		this.executionRepository = executionRepository;
		this.positionRepository = positionRepository;
		this.mktDataProvider = mktDataProvider;
		this.commissionModel = commissionModel;
	}

	@Override
	public Order bookOrder(Account account, OrderDto dto) {

		String symbol = dto.getSymbol();

		StockQuote quote = mktDataProvider.getStockQuote(symbol);
		Double lastPrice = quote.getLastPrice();
		Integer quantity = dto.getOpenQuantity();
		Double commission = commissionModel.calculateCommision(quantity);

		Order order = new Order();
		order.setAccount(account);
		order.setAvgExecPrice(lastPrice);
		order.setCanceledQuantity(0);
		order.setCommissionCharged(commission);
		order.setCreationTime(LocalDateTime.now());
		order.setFilledQuantity(quantity);
		order.setLastExecPrice(lastPrice);
		order.setOpenQuantity(0);
		order.setOrderType(dto.getOrderType());
		order.setSide(dto.getSide());
		order.setState(State.Executed);
		order.setSymbol(symbol);
		order.setTotalQuantity(quantity);
		order = orderRepository.saveAndFlush(order);
		account.getOrders().add(order);

		Execution execution = new Execution();
		execution.setAccount(account);
		execution.setCommission(commission);
		execution.setOrder(order);
		execution.setPrice(lastPrice);
		execution.setQuantity(quantity);
		execution.setSide(dto.getSide());
		execution.setSymbol(symbol);
		execution.setTimestamp(LocalDateTime.now());
		execution = executionRepository.saveAndFlush(execution);
		account.getExecutions().add(execution);

		Optional<Position> existingPosition = account.getPositions().stream().filter(ex -> ex.getSymbol().equalsIgnoreCase(symbol)).findFirst();
		if (existingPosition.isPresent()) {
			Position positionToUpdate = existingPosition.get();
			updatePositionFromExecution(positionToUpdate, execution);
			positionToUpdate = positionRepository.saveAndFlush(positionToUpdate);
		} else {
			Position newPosition = new Position();
			newPosition.setAccount(account);
			newPosition.setAverageEntryPrice(lastPrice);
			newPosition.setClosedPnL(0);
			newPosition.setOpenPnL(0);
			newPosition.setOpenQuantity(quantity);
			newPosition.setSymbol(symbol);
			newPosition.setTotalCost(quantity * lastPrice);
			newPosition = positionRepository.saveAndFlush(newPosition);
			account.getPositions().add(newPosition);
		}
		return order;
	}

	private void updatePositionFromExecution(Position position, Execution execution) {

		if (execution.getSide() == Side.Buy) {
			// We were short, we cover our position
			if (position.getOpenQuantity() < 0) {
				// We didn't cover all short position, we are still short
				if (position.getOpenQuantity() + execution.getQuantity() <= 0) {
					double closedPnl = position.getClosedPnL();
					closedPnl += execution.getQuantity() * (position.getAverageEntryPrice() - execution.getPrice());
					closedPnl -= execution.getCommission();
					position.setClosedPnL(closedPnl);
					position.setOpenQuantity(position.getOpenQuantity() + execution.getQuantity());
				}
				// We cover more position than needed, we are now long
				else {
					double closedPnl = position.getClosedPnL();
					closedPnl += Math.abs(position.getOpenQuantity()) * (position.getAverageEntryPrice() - execution.getPrice());
					closedPnl -= execution.getCommission();
					position.setClosedPnL(closedPnl);
					position.setOpenQuantity(position.getOpenQuantity() + execution.getQuantity());
					position.setAverageEntryPrice(execution.getPrice());
				}
			}
			// We buy more, we stay long
			else {
				int newPositionCount = position.getOpenQuantity() + execution.getQuantity();
				double averageCostPart1 = position.getAverageEntryPrice() * (position.getOpenQuantity() / (double) newPositionCount);
				double averageCostPart2 = execution.getPrice() * (execution.getQuantity() / (double) newPositionCount);
				position.setAverageEntryPrice(averageCostPart1 + averageCostPart2);
				position.setOpenQuantity(newPositionCount);
			}
		} else
		{
			// We were long, we sell some position
			if (position.getOpenQuantity() > 0) {
				// We sold not enough, we are still long
				if (position.getOpenQuantity() - execution.getQuantity() >= 0) {
					double closedPnl = position.getClosedPnL();
					closedPnl += execution.getQuantity() * (execution.getPrice() - position.getAverageEntryPrice());
					closedPnl -= execution.getCommission();
					position.setClosedPnL(closedPnl);
					position.setOpenQuantity(position.getOpenQuantity() - execution.getQuantity());
				}
				// We sold more than we had, we are short
				else {
					double closedPnl = position.getClosedPnL();
					closedPnl += Math.abs(position.getOpenQuantity()) * (execution.getPrice() - position.getAverageEntryPrice());
					closedPnl -= execution.getCommission();
					position.setClosedPnL(closedPnl);
					position.setOpenQuantity(position.getOpenQuantity() - execution.getQuantity());
					position.setAverageEntryPrice(execution.getPrice());
				}
			}
			// We sell more, we stay short
			else {
				int newPositionCount = position.getOpenQuantity() - execution.getQuantity();
				double averageCostPart1 = position.getAverageEntryPrice() * (position.getOpenQuantity() / (double) newPositionCount);
				double averageCostPart2 = execution.getPrice() * (-execution.getQuantity() / (double) newPositionCount);
				position.setAverageEntryPrice(averageCostPart1 + averageCostPart2);
				position.setOpenQuantity(newPositionCount);
			}
		}
	}

	@Override
	public Order modifyOrder(Account account, OrderDto dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Order deleteOrder(Account account, OrderDto dto) {
		throw new UnsupportedOperationException();
	}
}
