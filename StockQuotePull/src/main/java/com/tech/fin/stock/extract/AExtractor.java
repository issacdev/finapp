package com.tech.fin.stock.extract;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.tech.fin.network.HttpProxy;
import com.tech.fin.stock.dao.IStockMasterDao;
import com.tech.fin.stock.dao.IStockQuoteDao;

public abstract class AExtractor {

	protected IStockMasterDao masterDao;
	protected IStockQuoteDao quoteDao;
	protected HttpProxy httpProxy;
	protected String url;
	protected String marketCode;
	
	public void run() throws Exception{
		
	}
	
	public BufferedReader getBufferedReader(String urlStr, int skip){
		
		BufferedReader in = null;
		
		try{

			if(httpProxy != null){
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpProxy.getIp(), httpProxy.getPort()));
				System.setProperty("http.proxyUser", httpProxy.getUsername());
				System.setProperty("http.proxyPassword", httpProxy.getPassword());
	
				HttpURLConnection connection = (HttpURLConnection)new URL(urlStr).openConnection(proxy);
			
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}
			else{
				URL url = new URL(urlStr);
		        in = new BufferedReader(new InputStreamReader(url.openStream()));
			}
		
			in.skip(skip);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return in;
	}
	
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
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
	public HttpProxy getHttpProxy() {
		return httpProxy;
	}
	public void setHttpProxy(HttpProxy httpProxy) {
		this.httpProxy = httpProxy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
