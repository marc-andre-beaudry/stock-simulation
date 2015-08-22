package com.maillets.stocksimulation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock_profile")
public class StockProfile {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Lob
	@Column(length = 10000)
	private String summary;

	@OneToOne
	@MapsId
	private Stock stock;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
