package com.bn.dao;

import org.hibernate.HibernateException;

import com.bn.exception.BNStoreException;
import com.bn.pojo.CustomerOrder;;

public class OrdersDAO extends DAO {
	public CustomerOrder create(CustomerOrder orders) throws BNStoreException {
		try{
			begin();	
			getSession().save(orders);
			commit();
			return null;
		}
		catch(HibernateException e){
			rollback();
			throw new BNStoreException("Orders table cannot be created", e);
		}
		
	}
}
