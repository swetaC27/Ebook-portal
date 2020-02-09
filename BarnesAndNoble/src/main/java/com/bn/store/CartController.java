package com.bn.store;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bn.dao.BookDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Book;

@Controller
public class CartController {

	@RequestMapping(value="/addtocart.htm",method=RequestMethod.GET)
	protected ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response) throws BNStoreException {
		
		HttpSession session = request.getSession();
		
		ArrayList<Book> customerCart;
		if(session.getAttribute("customerCart")!= null) {
			customerCart = (ArrayList<Book>)session.getAttribute("customerCart");
		}
		else {
			customerCart = new ArrayList<Book>();
		}
		
		long id = Long.parseLong(request.getParameter("id"));
		
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.getBookById(id);
		
		customerCart.add(book);
		
		float totalAmount = 0;
		
		for(Book books:customerCart) {
			totalAmount += books.getPrice();
		}
		
		session.setAttribute("customerCart", customerCart);
		session.setAttribute("totalAmount", totalAmount);
		
		ModelAndView mv = new ModelAndView("displayCart", "customerCart", customerCart);
		mv.addObject("total", totalAmount);
		return mv;
		
	}
	
	
	@RequestMapping(value="/removeitem.htm",method=RequestMethod.GET)
	protected ModelAndView doAction(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) 
			throws BNStoreException {
		HttpSession session = request.getSession();
		ArrayList<Book> value;
		
		if(session.getAttribute("customerCart")!=null){
			value=(ArrayList<Book>)session.getAttribute("customerCart");
			System.out.println(id);		
			value.remove(id);
			
			float total = 0;
			for(Book b : value){
				total = total + b.getPrice();
			}
			session.setAttribute("totalAmount", total);
			ModelAndView mv = new ModelAndView("displayCart");
			return mv;
		}
		else{
			ModelAndView mv = new ModelAndView("displayCart");
			return mv;
		}
	}
	
	
	@RequestMapping(value="/viewcart.htm",method=RequestMethod.GET)
	protected String doViewAction(HttpServletRequest request, HttpServletResponse response) 
			throws BNStoreException {
		HttpSession session = request.getSession();
		ArrayList<Book> cart;
		if(session.getAttribute("customerCart")!=null){
			cart=(ArrayList<Book>)session.getAttribute("customerCart");
			return "displayCart";
		}
		else{
			return "displayCart";
		}
				
	}
	
	@RequestMapping(value="/cancelorder.htm",method=RequestMethod.GET)
	protected String doCancelAction(HttpServletRequest request, HttpServletResponse response) 
			throws BNStoreException {
		HttpSession session = request.getSession();
		ArrayList<Book> cart;
		if(session.getAttribute("customerCart")!=null){
			cart=(ArrayList<Book>)session.getAttribute("customerCart");
			return "displayCart";
		}
		else{
			return "displayCart";
		}
				
	}
}
