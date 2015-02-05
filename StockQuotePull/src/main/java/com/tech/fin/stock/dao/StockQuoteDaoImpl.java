package com.tech.fin.stock.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tech.fin.stock.entity.StockQuote;

public class StockQuoteDaoImpl implements IStockQuoteDao{
	
	private SessionFactory sessionFactory; 
	
	@Override
	@Transactional
	public void save(StockQuote quote) {
		sessionFactory.getCurrentSession().saveOrUpdate(quote);	
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
