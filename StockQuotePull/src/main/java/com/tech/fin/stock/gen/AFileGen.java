package com.tech.fin.stock.gen;

import com.tech.fin.stock.dao.IStockMasterDao;

public abstract class AFileGen {

	protected IStockMasterDao masterDao;
	protected String marketCode = "";
	protected String filePath = "";
	
	public void run() throws Exception{
		
	}
	
	public IStockMasterDao getMasterDao() {
		return masterDao;
	}
	public void setMasterDao(IStockMasterDao masterDao) {
		this.masterDao = masterDao;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
