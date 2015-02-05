package com.tech.fin.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK_QUOTE")
public class StockQuote implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOCK_KEY")
	private String stockKey;
	
	@Column(name = "OPEN")
	private BigDecimal open = BigDecimal.ZERO;
	
	@Column(name = "LAST")
	private BigDecimal last = BigDecimal.ZERO;
	
	@Column(name = "CLOSE")
	private BigDecimal close = BigDecimal.ZERO;
	
	@Column(name = "P_CLOSE")
	private BigDecimal previousClose = BigDecimal.ZERO;
	
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StockMaster stockMaster;
	
	public String getStockKey() {
		return stockKey;
	}

	public void setStockKey(String stockKey) {
		this.stockKey = stockKey;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public StockMaster getStockMaster() {
		return stockMaster;
	}

	public void setStockMaster(StockMaster stockMaster) {
		this.stockMaster = stockMaster;
	}
	
}


/*

@Entity
@Table(name = "STOCK_QUOTE")
public class StockQuote implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STOCK_KEY")
	private String stockKey;
	
	@Column(name = "OPEN")
	private BigDecimal open;
	
	@Column(name = "LAST")
	private BigDecimal last;
	
	@Column(name = "CLOSE")
	private BigDecimal close;
	
	@Column(name = "P_CLOSE")
	private BigDecimal previousClose;
	
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StockMaster stockMaster;
	
	public String getStockKey() {
		return stockKey;
	}

	public void setStockKey(String stockKey) {
		this.stockKey = stockKey;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public StockMaster getStockMaster() {
		return stockMaster;
	}

	public void setStockMaster(StockMaster stockMaster) {
		this.stockMaster = stockMaster;
	}
	
	
	
}


*/