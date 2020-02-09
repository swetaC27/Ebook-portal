package com.bn.store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bn.dao.AddressDAO;
import com.bn.dao.BookDAO;
import com.bn.dao.CustomerDAO;
import com.bn.dao.OrderDetailsDAO;
import com.bn.dao.OrdersDAO;
import com.bn.pojo.Address;
import com.bn.pojo.Book;
import com.bn.pojo.Customer;
import com.bn.pojo.CustomerOrder;
import com.bn.pojo.CustomerOrderDetails;


@Controller
public class CustomerOrderController {

	
	@RequestMapping(value="/confirmorder.htm")
	public String initializeForm() {
		return "orderSummary";
	}
	
	@RequestMapping(value="/confirmorder.htm", method=RequestMethod.POST)
	public ModelAndView confirmOrder(@ModelAttribute("orderSummary") CustomerOrder order, CustomerDAO customerDAO, BookDAO bookDAO,  HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		long userId = (Long)  session.getAttribute("userId");
		
		Customer customer = customerDAO.getCustomer(userId);
		
        ArrayList<Book> cart = (ArrayList<Book>)session.getAttribute("customerCart");
        
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    String orderDate = dateFormat.format(date);
        
	    int count=0;
	    for(Book b:cart){
	    	count++;
	    }
	    
        OrdersDAO ordersDao = new OrdersDAO();
        order.setOrderDate(orderDate);
        order.setCustomer(customer);
        order.setNoOfItems(count);
        ordersDao.create(order);
        
        HashMap<Long, Integer> record = new HashMap<Long, Integer>();
            
        
        for(Book b: cart){
        	if (record.containsKey(b.getBookId())) {
                record.put(b.getBookId(),record.get(b.getBookId()) + 1);
            }      
            else {
                record.put(b.getBookId(), 1);
            } 
        	CustomerOrderDetails od = new CustomerOrderDetails();
        	OrderDetailsDAO odDao = new OrderDetailsDAO();
        	
        	od.setBookId(b.getBookId());
        	od.setPrice(b.getPrice());
        	od.setBookName(b.getTitle());
        	od.setBookAuthor(b.getAuthor());
        	System.out.print(b.getBookId() + " ");
        	System.out.print(b.getQuantity() + " ");
        	od.setOrderId(order);
        	od.setCustomerId(userId);
        	odDao.create(od);
        	
        }

        Set<HashMap.Entry<Long, Integer>> set = record.entrySet();
        
        for(java.util.Map.Entry<Long, Integer> entry: record.entrySet()) {
        	Book book = bookDAO.getBookById(entry.getKey()); 
        	bookDAO.update(book.getQuantity() - entry.getValue(), entry.getKey());
        }
        
        customer.getOrders().add(order);
        customerDAO.update(customer);
        
        session.removeAttribute("cart");
        session.setAttribute("orderId", order.getOrderId());
        
        ModelAndView mv = new ModelAndView("orderedItems");
        
        try {
			SendEmail(customer);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
		return mv;
		
		
	}
	
	@RequestMapping(value="/proceedtocheckout.htm", method=RequestMethod.POST)
	public ModelAndView proceedToCheckout(HttpServletRequest request, AddressDAO addressDAO) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		long userId = (Long)  session.getAttribute("userId");
		
		Address address = addressDAO.getAddress(userId);
		mv.addObject("address", address);
		
		ArrayList<Book> customerCart;
		if(session.getAttribute("customerCart")!= null) {
			customerCart = (ArrayList<Book>)session.getAttribute("customerCart");
		}
		else {
			customerCart = new ArrayList<Book>();
		}
		
		mv.addObject("customerCart", session.getAttribute("customerCart"));
		mv.addObject("total", session.getAttribute("totalAmount"));
		mv.addObject("customer", session.getAttribute("customer"));
		mv.setViewName("orderSummary");
		return mv;
	}
	

	@RequestMapping(value ="/orderhistory.htm", method=RequestMethod.GET)
	protected ModelAndView doSubmitAction(HttpServletRequest request) throws Exception
    {
		//HttpSession session = request.getSession();
		//long userId = (Long) session.getAttribute("userId");
		//System.out.println(userId);
		
		    OrderDetailsDAO orderDetails = new OrderDetailsDAO();
	        //long custid = 0;
			List odList = orderDetails.listOrders();
	        
	        System.out.print("the od list says that :::: "+ odList);
	        
	        ModelAndView mv = new ModelAndView("orderHistory","od", odList);
	        
	        //pageContext.setAttribute("odList", odList);
		return mv;	
	}
	
	@RequestMapping(value="/returnfromorder.htm", method=RequestMethod.GET)
	public String goBack(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("customerCart");
		mv.setViewName("displayBooks");
		return "redirect:/displaybooks.htm";
	}
	

	
	
	
	
	private void SendEmail(Customer customer) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("swetsknowsnorulez@gmail.com", "Sweta@123"));
		email.setSSLOnConnect(true);
		email.setFrom("swetsknowsnorulez@gmail.com");
		email.setSubject("Barnes&Noble order summary");
		email.setMsg("Your order will be delivered in 2-3 business days");
		email.addTo(customer.getEmailId());
		email.send();
	}
}

