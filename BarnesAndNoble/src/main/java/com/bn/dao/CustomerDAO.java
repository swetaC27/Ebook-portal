package com.bn.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.bn.exception.BNStoreException;
import com.bn.pojo.Address;
import com.bn.pojo.Customer;
import com.bn.pojo.User;

public class CustomerDAO extends DAO {

	public CustomerDAO() {

	}

	public Customer create(String firstName, String lastName, String emailID, String roleName, String userName,
			String password, String addressLine1, String addressLine2, String city, String zip, String state,
			String country, String phone, String gender) throws BNStoreException {
		try {
			begin();
			Customer customer = new Customer(firstName, lastName, gender, emailID, phone);
			customer.setRoleName(roleName);
			customer.setUserName(userName);
			customer.setPassword(password);
			Address address = new Address(addressLine1, addressLine2, city, zip, state, country);
			customer.setAddress(address);

			getSession().save(customer);
			commit();
			return customer;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while creating customer: " + ex.getMessage());
		}

	}
	
	public Customer update(Customer customer) throws BNStoreException {
		try {
			begin();
			getSession().update(customer);
			commit();
			return customer;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while creating customer: " + ex.getMessage());
		}

	}

	public List<Customer> list() throws BNStoreException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer");
			List<Customer> customerList = q.list();
			commit();
			return customerList;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while creating customer: " + ex.getMessage());
		}

	}


	
	public Customer getCustomer(long id) throws BNStoreException {
		try {
			begin();
			Customer customer = (Customer) getSession().get(Customer.class, id);
			commit();
			return customer;
		} catch (HibernateException ex) {
			rollback();
			throw new BNStoreException("Exception while fetching customer: " + ex.getMessage());
		}

	}
	
	
	public List<User> listUsername() throws BNStoreException {
        try {
            begin();
            Query q = getSession().createQuery("from User");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new BNStoreException("Could not list the users", e);
        }
    }

}
