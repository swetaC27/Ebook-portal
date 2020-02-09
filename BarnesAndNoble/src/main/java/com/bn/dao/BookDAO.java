package com.bn.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.bn.exception.BNStoreException;
import com.bn.pojo.Book;

public class BookDAO extends DAO {

	public Book create(Book book) throws BNStoreException {

		try {
			begin();
			getSession().save(book);
			commit();
			return book;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while creating book: " + ex.getMessage());
		}
	}

	public List<Book> getBooks() throws BNStoreException {
		try {
			begin();
			Query q = getSession().createQuery("from Book");
			List<Book> books = q.list();
			commit();
			return books;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Could not list the books", ex);
		}
	}

	public List<Book> getBooks(String key, String flag) throws BNStoreException {
		try {
			begin();

//			Query q = getSession().createQuery("From Book b where b." + flag + "= :value");
//			q.setParameter("value", key);
//			List<Book> list = new ArrayList<Book>();
//			list = q.list();
//			commit();
//			return list;
			
			Criteria c = getSession().createCriteria(Book.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("isbn")); 
			projList.add(Projections.property("title")); 
			projList.add(Projections.property("author")); 
			projList.add(Projections.property("price")); 
			c.setProjection(projList);
			if(!flag.equals("isbn")) {
				c.add(Restrictions.ilike(flag, key, MatchMode.ANYWHERE));
			}
			else {
				c.add(Restrictions.eq(flag, key));
			}
			List<Book> list = c.list(); 
			commit();
			return list;
			
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Could not list the books", e);
		}
	}

	public Book getBookById(long id) throws BNStoreException {
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookId= :id");
			q.setLong("id", id);
			Book book = (Book) q.uniqueResult();
			// getSession().save(book);
			commit();
			return book;
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Could not find book", e);
		}

	}
	
	public int update(int qty, long bookId) throws BNStoreException {

		try {
			begin();
			Query q = getSession().createQuery("update Book set quantity =:quantity where bookId =:bookId");
			q.setInteger("quantity", qty);
			q.setLong("bookId", bookId);
			int i = q.executeUpdate();
			commit();
			return i;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while updating book: " + ex.getMessage());
		}
	}
	
	public void delete(long id) throws BNStoreException {

		try {
			begin();
			Query q = getSession().createQuery("delete from Book where bookId =:bookId");
			q.setLong("bookId", id);
			q.executeUpdate();
			commit();
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while deleting book: " + ex.getMessage());
		}
	}

}
