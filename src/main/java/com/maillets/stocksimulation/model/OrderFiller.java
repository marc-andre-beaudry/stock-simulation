package com.maillets.stocksimulation.model;

import java.util.concurrent.BlockingQueue;

import com.maillets.stocksimulation.entities.Order;

public class OrderFiller implements Runnable {

	private final BlockingQueue<Order> queue;
	private volatile boolean isRunning = true;

	public OrderFiller(BlockingQueue<Order> queue) {
		this.queue = queue;
	}

	public synchronized void stop() {
		isRunning = false;
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				Order order = queue.take();
				
				
				Thread.sleep(10000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
