package com.tech.fin.stock.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tech.fin.stock.dao.IStockMasterDao;
import com.tech.fin.stock.entity.StockMaster;

public class BossPriceFileGen {

	private IStockMasterDao masterDao;
	private String marketCode = "";
	private String filePath = "";

	public void run(){
	
		List<StockMaster> masterList = masterDao.getByMarket(marketCode);
		DecimalFormat df = new DecimalFormat("0000000.00000");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		File file = new File(filePath);

		try{
			
			if(file.exists()){
				file.delete();
			}
			
			file.createNewFile();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		for(StockMaster master : masterList){
			
    		StringBuffer dataFeed = new StringBuffer();
    		dataFeed.append("3SHA");
    		
    		//stock code
    		dataFeed.append(master.getStockCode());
    		
    		//stock name
    		if(StringUtils.isEmpty(master.getStockName())){
    			dataFeed.append(String.format("%-15s", master.getStockName()));
    		}
    		else if(master.getStockName().length() > 15){
    			dataFeed.append(String.format("%-15s", master.getStockName().substring(0, 15)));
    		}
    		else{
    			dataFeed.append(String.format("%-15s", master.getStockName()));
    		}
    		
    		//currency
    		dataFeed.append("CNY");
    		
    		//last price
    		if(master.getStockQuote() == null){
    			dataFeed.append(df.format(0));
    		}
    		else{
    			dataFeed.append(df.format(master.getStockQuote().getLast()));
    		}
			
    		//current date
    		dataFeed.append(dateFormat.format(new Date()));
    		
    		//4 flags
    		dataFeed.append("NNNN");
    		
    		dataFeed.append(String.format("%8s",  ""));
    		
    		System.out.println(dataFeed.toString());
    		
    		try{
	    		FileWriter fileWritter = new FileWriter(filePath,true);
		        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		        bufferWritter.write(dataFeed.toString());
		        bufferWritter.newLine();
		        bufferWritter.close();
    		}catch(Exception e){
    			e.printStackTrace();
    		}
		}
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

