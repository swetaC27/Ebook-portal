package com.bn.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.bn.exception.BNStoreException;
import com.bn.pojo.Genre;

public class GenreDAO extends DAO {

	public Genre create(Genre genre) throws BNStoreException {
		try {
			begin();
			getSession().save(genre);
			commit();
			return genre;
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Exception while creating genre: " + e.getMessage());
		}
	}

	public List<Genre> list() throws BNStoreException {
		try {
			begin();
			Query<Genre> q = getSession().createQuery("from Genre");
			List genreList = q.list();
			commit();
			return genreList;
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Exception while creating genre: " + e.getMessage());
		}

	}

	public Genre get(String genreName) throws BNStoreException {
		try {
			begin();
			Query q = getSession().createQuery("from Genre where genreName= :genreName");
			q.setString("genreName", genreName);
			Genre genre = (Genre) q.uniqueResult();
			commit();
			return genre;
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Could not obtain the named genre " + genreName + " " + e.getMessage());
		}
	}

	public void save(Genre genre) throws BNStoreException {
		try {
			begin();
			getSession().update(genre);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Could not save the genre", e);
		}
	}

}
