package com.tech.fin.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.tech.fin.stock.extract.AExtractor;
import com.tech.fin.stock.gen.AFileGen;

public class BossPriceBatchJob implements IJob{

	private ArrayList<AExtractor> extractorList;
	
	private ArrayList<AFileGen> fileGenList;

	public static Logger log = Logger.getLogger(BossPriceBatchJob.class.getName());

	@Override
	public void run(){
		
		try{
			fileCompare();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		if(extractorList.size() != fileGenList.size()){
			log.debug("wrong setting");
			return;
		}
		
		int length = extractorList.size();
		
		for(int i=0; i<length; i++){
			
			System.out.println("Job Execution: " + i);
			log.debug("Job Execution: " + i);
			
			try{
				AExtractor extractor = (AExtractor)extractorList.get(i);
				extractor.run();
				
				AFileGen fileGen = (AFileGen)fileGenList.get(i);
				fileGen.run();
			}catch(Exception e){
				log.debug(e);
			}
		}
		
		try{
			
			if(fileCompare()){
				System.out.println("File Gen Completed Successfully");
				log.debug("File Gen Completed Successfully");
			}
			else{
				System.out.println("File Gen Fail");
				log.debug("File Gen Fail");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean fileCompare() throws FileNotFoundException, IOException{
		
	    File file1 = new File(fileGenList.get(0).getFilePath());
		File file2 = new File(fileGenList.get(1).getFilePath());

		boolean compare1and2 = FileUtils.contentEquals(file1, file2);
		
		return compare1and2;
	}
	 
	public ArrayList<AExtractor> getExtractorList() {
		return extractorList;
	}

	public void setExtractorList(ArrayList<AExtractor> extractorList) {
		this.extractorList = extractorList;
	}

	public ArrayList<AFileGen> getFileGenList() {
		return fileGenList;
	}

	public void setFileGenList(ArrayList<AFileGen> fileGenList) {
		this.fileGenList = fileGenList;
	}
}
