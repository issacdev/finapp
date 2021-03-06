package com.tech.fin.stock.extract;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tech.fin.network.HttpProxy;
import com.tech.fin.stock.dao.IStockMasterDao;
import com.tech.fin.stock.dao.IStockQuoteDao;
import com.tech.fin.stock.entity.StockMaster;
import com.tech.fin.stock.entity.StockQuote;

public class AastocksExtractor extends AExtractor{
	
	private int skip = 60000; 
	private String riseKey = "<b><span class=\'pos\'><span class=\'arr_ud arrow_u5\'> </span>&nbsp;";
	private String dropKey = "<b><span class=\'neg\'><span class=\'arr_ud arrow_d6\'> </span>&nbsp;";
	private String unchangedKey = "<b><span class=\"cls ss2\">";
	
	public static Logger log = Logger.getLogger(AastocksExtractor.class.getName());
	
	public void run() throws Exception{
		
		System.out.println("#### AASTOCKS EXTRACTOR");
		
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
			
			BufferedReader in = getBufferedReader(tempUrl, skip);
	        
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
		        	
	        		String price = (inputLine).substring((count3 + searchKey.length()), (count3 + searchKey.length()) + 10);
	        		
	        		if(price.contains("<")){
	        			price = price.substring(0, price.indexOf("<"));
	        		}
		        	
	        		System.out.println("# " + master.getStockCode() + " - " + price);
	        		
	        		quote.setLast(new BigDecimal(price));
	        		masterDao.save(master);
	        		
	        		break;
	        	}
	        }
	        in.close();
		}
	}	
}
