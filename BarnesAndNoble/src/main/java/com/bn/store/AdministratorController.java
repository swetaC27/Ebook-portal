package com.bn.store;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class AdministratorController {

	@RequestMapping(value="/managebooks.htm", method = RequestMethod.GET)
	public String showManageBooks(HttpServletRequest request) {
		return "manageBooks";
	}
	
	@RequestMapping(value="/vieworders.htm", method = RequestMethod.GET)
	protected String viewOrderAction(HttpServletRequest request){
		return "viewOrders";
		
	}
	
	@RequestMapping(value="/viewcustomers.htm", method = RequestMethod.GET)
	protected String viewCustomerAction(HttpServletRequest request){
		return "viewCustomers";
		
	}
	
	
}



