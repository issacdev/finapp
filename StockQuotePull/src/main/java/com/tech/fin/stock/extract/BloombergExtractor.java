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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.tech.fin.stock.dao.IStockMasterDao;
import com.tech.fin.stock.dao.IStockQuoteDao;
import com.tech.fin.stock.entity.StockMaster;
import com.tech.fin.stock.entity.StockQuote;
import com.tech.fin.stock.main.Main;

public class BloombergExtractor extends AExtractor{
	
	private int skip = 0; 
	private String searchKey = "<meta itemprop=\"price\" content=\"";
	
	public static Logger log = Logger.getLogger(AastocksExtractor.class.getName());
	
	public void run() throws Exception{
		
		System.out.println("### BL");
		List<StockMaster> masterList = masterDao.getByMarket(marketCode);
		
		for(StockMaster master : masterList){
			
			//Stock Key
			String tempUrl = url.replace("##STOCK_CODE##", master.getStockCode());
	        String inputLine;
	        
			StockQuote quote = master.getStockQuote();
			if(quote == null){
				quote = new StockQuote();
				quote.setStockKey(master.getStockKey());
				quote.setUpdatedTime(new Date());
				master.setStockQuote(quote);
			}
			
			BufferedReader in = getBufferedReader(tempUrl, skip);
	    
	        while ((inputLine = in.readLine()) != null){
	        	
	        	inputLine = inputLine.trim();
	        	
	        	if(inputLine.contains(searchKey)){
		        	
		        	int count = inputLine.indexOf(searchKey);
		        	
	        		String price = (inputLine).substring((count + searchKey.length()), (count + searchKey.length()) + 8 );
	        		
	        		if(price.contains("\"")){
	        			price = price.substring(0, price.indexOf("\"") - 1);
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
