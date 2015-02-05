package com.tech.fin.stock.dao;

import java.util.List;

import com.tech.fin.stock.entity.StockMaster;

public interface IStockMasterDao {

	public void save(StockMaster master);
	
	public List<StockMaster> getAll();
	
	public List<StockMaster> getByMarket(String marketCode);
	
	public StockMaster get(String stockCode);
}
