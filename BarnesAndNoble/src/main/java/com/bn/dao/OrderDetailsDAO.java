package com.bn.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.bn.exception.BNStoreException;
import com.bn.pojo.CustomerOrderDetails;

public class OrderDetailsDAO extends DAO {
	
	public CustomerOrderDetails create(CustomerOrderDetails orderDetails) throws BNStoreException {
		try{
			begin();	
			getSession().save(orderDetails);
			commit();
			return null;
		}
		catch(HibernateException e){
			rollback();
			throw new BNStoreException("Order Details table cannot be created", e);
		}
		
	}
	//List for pdfview:::
	
	  public List listDetails(long userId) throws BNStoreException {
	        try {
	            begin();
	           // long userid = (Long)session.getAttribute("userId");
	            Query q = getSession().createQuery("from CustomerOrderDetails where customerId= :userId");
	            q.setLong("userId", userId);
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new BNStoreException("Could not list the genres", e);
	        }
	    }
	
	  
	  public List listOrders() throws BNStoreException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from CustomerOrderDetails");
	            List list = q.list();
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            throw new BNStoreException("Could not list the order details! Try again.", e);
	        }
	    }

}
