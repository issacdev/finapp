package com.tech.fin.stock.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tech.fin.stock.entity.StockMaster;

public class StockMasterDaoImpl implements IStockMasterDao{

	private SessionFactory sessionFactory; 
	
	@Override
	@Transactional
	public void save(StockMaster master) {
		sessionFactory.getCurrentSession().saveOrUpdate(master);	
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public StockMaster get(String stockCode) {
		
		Session session = sessionFactory.openSession(); 
	        
        StockMaster master = (StockMaster) session.get(StockMaster.class, stockCode);
        
        session.close();
        
        return master;
	}

	@Override
	public List<StockMaster> getAll() {
		
		Session session = sessionFactory.openSession(); 
		return session.createCriteria(StockMaster.class).list();
	}

	@Override
	public List<StockMaster> getByMarket(String marketCode) {
		
		Session session = sessionFactory.openSession(); 

		SQLQuery query = session.createSQLQuery("select * from stock_master where market_code = :marketCode");
		query.addEntity(StockMaster.class);
		query.setParameter("marketCode", marketCode);
		
		return query.list();
	}

}
