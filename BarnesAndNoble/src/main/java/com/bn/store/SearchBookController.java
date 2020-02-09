package com.bn.store;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bn.dao.BookDAO;
import com.bn.pojo.Book;



@Controller
public class SearchBookController {
		
	
	@RequestMapping(value="/searchbooks.htm", method=RequestMethod.GET)
	public ModelAndView viewSearchForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("searchBook");
	    return mv; 
	}
	
	
	@RequestMapping(value = "/search.htm", method = RequestMethod.POST)
	public ModelAndView searchBook(HttpServletRequest request, HttpServletResponse response, BookDAO bookDAO) throws Exception {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("search")) {
			List<Book> bookList = new ArrayList<Book>();
			List copy = new ArrayList();
			
			String key =  request.getParameter("key");
			String flag = request.getParameter("flag");
			
			bookList = bookDAO.getBooks(key, flag);	
			
			JSONObject json = new JSONObject();
			json.put("books", bookList);
			PrintWriter writer = response.getWriter();
			writer.println(json);	
		}
		return null;

	}
	
	
}
