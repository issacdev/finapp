package com.tech.fin.stock.extract;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.tech.fin.stock.dao.IStockMasterDao;
import com.tech.fin.stock.dao.IStockQuoteDao;
import com.tech.fin.stock.entity.StockMaster;
import com.tech.fin.stock.entity.StockQuote;

public class AastocksExtractor {

	
	private IStockMasterDao masterDao;
	private IStockQuoteDao quoteDao;
	
	private String marketCode = "";
	private String url = "";
	private int skip = 60000; 
	private String riseKey = "<b><span class=\'pos\'><span class=\'arr_ud arrow_u5\'> </span>&nbsp;";
	private String dropKey = "<b><span class=\'neg\'><span class=\'arr_ud arrow_d6\'> </span>&nbsp;";
	private String unchangedKey = "<b><span class=\"cls ss2\">";
	
	public void run(){
		
		List<StockMaster> masterList = masterDao.getByMarket(marketCode);

		for(StockMaster master : masterList){
			
			String tempUrl = url.replace("##STOCK_CODE##", master.getStockCode());
			
			StockQuote quote = master.getStockQuote();
			if(quote == null){
				quote = new StockQuote();
				quote.setStockKey(master.getStockKey());
				quote.setUpdatedTime(new Date());
				master.setStockQuote(quote);
			}
			
			try{
				
				URL oracle = new URL(tempUrl);
		        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
		        in.skip(skip);
		        
		        String inputLine;
		        
		        while ((inputLine = in.readLine()) != null){
		        	
		        	inputLine = inputLine.trim();
		        	
		        	String searchKey = "";
		        	
		        	if(inputLine.contains(riseKey)){
		        		searchKey = riseKey;
		        	}
		        	else if(inputLine.contains(dropKey)){
		        		searchKey = dropKey;
		        	}
		        	else if(inputLine.contains(unchangedKey)){
		        		searchKey = unchangedKey;
		        	}
		        	
		        	if(searchKey.length() > 0){
			        	
			        	int count3 = inputLine.indexOf(searchKey);
			        	
		        		String price = (inputLine).substring((count3 + searchKey.length()), (count3 + searchKey.length())+5);
		        		
	
		        		if(price.contains("<")){
		        			price = price.substring(0, price.length() - 1);
		        		}
			        	
		        		System.out.println("# " + master.getStockCode() + " - " + price);
		        		
		        		break;
		        	}
		        }
		        in.close();
		        
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

	public IStockQuoteDao getQuoteDao() {
		return quoteDao;
	}

	public void setQuoteDao(IStockQuoteDao quoteDao) {
		this.quoteDao = quoteDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
}
