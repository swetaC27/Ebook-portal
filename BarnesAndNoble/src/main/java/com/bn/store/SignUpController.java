package com.bn.store;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bn.dao.CustomerDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Customer;
import com.bn.pojo.User;
import com.bn.validator.CustomerValidator;


@Controller
public class SignUpController {
	
	@Autowired
	CustomerValidator signUpValidator;

	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public String showSignUpForm(@ModelAttribute("customer") Customer customer, BindingResult result) {
		return "signUp";
	}
	
	@RequestMapping(value = "/signupsuccess.htm", method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("customer") Customer customer, BindingResult result, HttpServletRequest request, CustomerDAO customerDAO) {
		
		signUpValidator.validate(customer, result);
		try {
			
			if (result.hasErrors()) {
				return new ModelAndView("signUp");
			}
			customerDAO.create(customer.getFirstName(), customer.getLastName(), customer.getEmailId(), customer.getRoleName(), customer.getUserName(), customer.getPassword(),
					request.getParameter("addressLine1"), request.getParameter("addressLine2"), request.getParameter("city"),request.getParameter("zip"), 
					request.getParameter("state"), request.getParameter("country"), customer.getPhone(), customer.getGender()); 
					

			
		} catch (BNStoreException e) {
			e.printStackTrace();
		}
		return new ModelAndView("signUpSuccess", "customer", customer);
	}	
	
}
