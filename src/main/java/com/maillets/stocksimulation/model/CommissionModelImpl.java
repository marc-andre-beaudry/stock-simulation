package com.maillets.stocksimulation.model;

public class CommissionModelImpl implements CommissionModel {

	public Double calculateCommision(int size) {
		return size * 0.01 + 1;
	}
}
