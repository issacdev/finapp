package com.tech.fin.stock.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.tech.fin.stock.entity.StockMaster;

public class BossPriceFileGen extends AFileGen{

	public static Logger log = Logger.getLogger(BossPriceFileGen.class.getName());
	
	public void run() throws Exception{
	
		List<StockMaster> masterList = masterDao.getByMarket(marketCode);
		DecimalFormat df = new DecimalFormat("0000000.00000");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		String currentTime = timeFormat.format(new Date());
		
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		
		StringBuffer header = new StringBuffer();
		header.append("0AS OF  ");
		header.append(currentDate);
		header.append("                                    ");
		header.append("12:00:00");
		header.append(" ");
		FileWriter fileWritter = new FileWriter(filePath,true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(header.toString());
        bufferWritter.newLine();
        bufferWritter.close();
        
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
    		log.debug(dataFeed.toString());
    		
    		fileWritter = new FileWriter(filePath,true);
	        bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write(dataFeed.toString());
	        bufferWritter.newLine();
	        bufferWritter.close();
		}
		
		StringBuffer tailer = new StringBuffer();
		tailer.append("9");
		tailer.append(String.format("%010d", masterList.size()));
		tailer.append("                                                 ");
		fileWritter = new FileWriter(filePath,true);
        bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(tailer.toString());
        bufferWritter.newLine();
        bufferWritter.close();
	}	
}

