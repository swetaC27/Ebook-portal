package com.bn.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.bn.exception.BNStoreException;
import com.bn.pojo.User;

public class UserDAO extends DAO {

	public User verifyUser(String userName, String password, String roleName) throws BNStoreException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName= :userName AND password= :password");
			q.setString("userName", userName);
			q.setString("password", password);

			User user = (User) q.uniqueResult();
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new BNStoreException("Username and password are invalid!", e);
		}

	}
}
