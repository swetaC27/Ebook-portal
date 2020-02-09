package com.bn.store;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bn.dao.CustomerDAO;
import com.bn.dao.UserDAO;
import com.bn.pojo.Customer;
import com.bn.pojo.User;
import com.bn.validator.UserValidator;

@Controller
public class LoginPageController {

	@Autowired
	UserValidator userValidator;

	@RequestMapping(value = "/login.htm")
	public String showLoginForm(@ModelAttribute("user") User user, BindingResult result) {
		return "loginPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loggedin.htm")
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result, UserDAO userDAO, CustomerDAO customerDAO,
			HttpServletRequest request) throws Exception {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "loginPage";
		} else {
			HttpSession session;
			session = request.getSession();

			try {

				User verifyUser = userDAO.verifyUser(user.getUserName(), user.getPassword(), user.getRoleName());
				if (verifyUser != null) {
					if (verifyUser.getRoleName().equals("admin")) {
						session.setAttribute("username", verifyUser.getUserName());
						session.setAttribute("role", "admin");
						session.setAttribute("userId", verifyUser.getUserId());
						return "adminLoggedIn";
					} else if (verifyUser.getRoleName().equals("customer")) {
						session.setAttribute("username", verifyUser.getUserName());
						session.setAttribute("role", "customer");
						session.setAttribute("userId", verifyUser.getUserId());
						Customer customer = customerDAO.getCustomer(verifyUser.getUserId());
						session.setAttribute("customer", customer);
						System.out.println(verifyUser.getUserId());
						return "customerLoggedIn";
					} else {
						System.out.println("Login has failed.");
						return "loginFailed";
					}
				} else {
					System.out.println("User not Found.");
					return "loginFailed";
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			return "loginPage";

		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout.htm")
	public String logOut(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request)
			throws Exception {
		request.getSession().invalidate();
		return "loginPage";
	}

}
