package com.bn.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DAO {

	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal sessionThread = new ThreadLocal();
	private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	
	public static Session getSession() {
		
		Session session = (Session) DAO.sessionThread.get();
		
		if(session == null) {
			session = sf.openSession();
			DAO.sessionThread.set(session);
		}
		
		return session;
	}
	
	
	protected void begin() {
		 getSession().beginTransaction();
	}
	
	protected void commit() {
		getSession().getTransaction().commit();
		//close();
	}
	
	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		}
		catch(HibernateException ex) {
			log.log(Level.WARNING,"Cannot rollback", ex);
		}
		
		try {
			getSession().close();
		}
		catch(HibernateException ex) {
			log.log(Level.WARNING, "Cannot close", ex);
		}
		DAO.sessionThread.set(null);
	}
	
	protected void close() {
		getSession().close();
		DAO.sessionThread.set(null);
	}
	
	
}
