package com.tech.fin.stock.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK_MASTER")
public class StockMaster implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOCK_KEY")
	private String stockKey;
	
	@Column(name = "MARKET_CODE")
	private String marketCode;
	
	@Column(name = "STOCK_CODE")
	private String stockCode;
	
	@Column(name = "STOCK_NAME")
	private String stockName;

	@OneToOne(mappedBy = "stockMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
	private StockQuote stockQuote;
	
	public String getStockKey() {
		return stockKey;
	}

	public void setStockKey(String stockKey) {
		this.stockKey = stockKey;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public StockQuote getStockQuote() {
		return stockQuote;
	}

	public void setStockQuote(StockQuote stockQuote) {
		this.stockQuote = stockQuote;
	}
}


/*

package com.tech.fin.stock.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK_MASTER")
public class StockMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOCK_KEY")
	private String stockKey;
	
	@Column(name = "MARKET_CODE")
	private String marketCode;
	
	@Column(name = "STOCK_CODE")
	private String stockCode;
	
	@Column(name = "STOCK_NAME")
	private String stockName;

	@OneToOne(mappedBy = "stockMaster", cascade = CascadeType.ALL)  
	@JoinColumn( name="stockKey")
	private StockQuote stockQuote;
	
	public String getStockKey() {
		return stockKey;
	}

	public void setStockKey(String stockKey) {
		this.stockKey = stockKey;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public StockQuote getStockQuote() {
		return stockQuote;
	}

	public void setStockQuote(StockQuote stockQuote) {
		this.stockQuote = stockQuote;
	}
	
	
	
}



 */
