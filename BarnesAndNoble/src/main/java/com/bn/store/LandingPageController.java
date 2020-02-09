package com.bn.store;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bn.pojo.Customer;


@Controller
public class LandingPageController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLandingPage() {
		return "landingPage";
	}
}
