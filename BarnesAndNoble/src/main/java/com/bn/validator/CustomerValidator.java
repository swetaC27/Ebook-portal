package com.bn.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bn.dao.CustomerDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Address;
import com.bn.pojo.Customer;
import com.bn.pojo.User;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> classz) {
		return Customer.class.isAssignableFrom(classz) || Address.class.isAssignableFrom(classz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Customer customer = (Customer) obj;

		CustomerDAO customerDao = new CustomerDAO();
		List<User> user;
		try {
			user = customerDao.listUsername();
			
			if(customer.getUserName().equalsIgnoreCase("admin")) {
				errors.rejectValue("userName", "username.cannotUse", "Please use a different username");
			}
			for (User u : user) {
				if (u.getUserName().equals(customer.getUserName())) {

					errors.rejectValue("userName", "username.alreadyExist", "Username already exists");
				}			
			}
			for(Customer c: customerDao.list()) {
				if (c.getEmailId().equals(customer.getEmailId())) {
					errors.rejectValue("emailId", "email.alreadyExist", "Email already exists");
				}	
			}
		} catch (BNStoreException e) {
			e.printStackTrace();
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName",
				"First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.emailId", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.username", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.invalid.phone", "Phone Required");

	}
}
