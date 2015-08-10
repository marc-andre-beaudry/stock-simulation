package com.maillets.stocksimulation.model;

import com.maillets.stocksimulation.dto.OrderDto;
import com.maillets.stocksimulation.entities.Account;
import com.maillets.stocksimulation.entities.Order;

public interface OrderBooker {

	Order bookOrder(Account account, OrderDto dto);

	Order modifyOrder(Account account, OrderDto dto);
	
	Order deleteOrder(Account account, OrderDto dto);
}
