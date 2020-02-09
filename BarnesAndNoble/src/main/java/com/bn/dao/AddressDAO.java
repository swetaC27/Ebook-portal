package com.bn.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.bn.exception.BNStoreException;
import com.bn.pojo.Address;
import com.bn.pojo.Customer;

public class AddressDAO extends DAO {

	
	public Address getAddress(long id) throws BNStoreException {
	try {
		begin();
		Address address = (Address) getSession().get(Address.class, id);
//		
//		String hql = "from Customer as cust inner join cust.address as address where cust.";
//		List<?> list = getSession().createQuery(hql).list();		
//		for(int i=0; i<list.size(); i++) {
//			Object[] row = (Object[]) list.get(i);
//			Customer customer = (Customer)row[0];
//			Address address = (Address)row[1];
//			System.out.print(customer.getFirstName() + address.getAddressLine1());
//		}		
		
		commit();
		return address;
	} catch (HibernateException ex) {
		rollback();
		throw new BNStoreException("Exception while fetching address: " + ex.getMessage());
	}

}
}
