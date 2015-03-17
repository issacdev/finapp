package com.tech.fin.stock.main;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tech.fin.batch.IJob;
import com.tech.fin.stock.gen.BossPriceFileGen;

public class Main {

	public static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String args[]){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springContext.xml");
		
		//BloombergExtractor extor = (BloombergExtractor)ctx.getBean("bloombergExtractor");
		//extor.run();
		
		//YahooExtractor extor = (YahooExtractor)ctx.getBean("yahooExtractor");
		//extor.run();
		
		IJob job = (IJob)ctx.getBean("bossPriceBatchJob");
		job.run();
		
		//BossPriceFileGen aastocksfileGen = (BossPriceFileGen)ctx.getBean("aastocksBossPriceFileGen");
		//aastocksfileGen.run();
		
		/*
		IStockMasterDao dao = (IStockMasterDao)ctx.getBean("stockMasterDaoImpl");
		IStockQuoteDao quoteDao = (IStockQuoteDao)ctx.getBean("stockQuoteDaoImpl");
		
		StockMaster master = new StockMaster();
		
		
		master.setStockKey("600004.SS");
		master.setMarketCode("SS");
		master.setStockCode("600004");
		master.setStockName("TEST");
		
		StockQuote quote = new StockQuote();
		quote.setStockKey("600004.SS");
		quote.setOpen(new BigDecimal("3456.23"));
		//master.setStockQuote(quote);
		dao.save(master);
		*/
		
		/*
		StockMaster master2 = dao.get("600003.ss");
		
		if(master2 != null){
			System.out.println(master2.getStockCode());
			
			log.debug("### " + master2.getMarketCode());
			
			if(master2.getStockQuote() != null){
				System.out.println("####" + master2.getStockQuote().getOpen());
			}
		}*/
		
		/*
		StockQuote quote = new StockQuote();
		quote.setStockKey("600001.SS");
		quote.setOpen(new BigDecimal("3456.23"));
		quoteDao.save(quote);
		*/
		

		
	}
}
