package com.bn.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bn.dao.BookDAO;
import com.bn.dao.CustomerDAO;
import com.bn.dao.GenreDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Book;
import com.bn.pojo.Customer;
import com.bn.pojo.Genre;
import com.bn.validator.BookValidator;

@Controller
public class BookController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	@Qualifier("bookValidator")
	BookValidator bookValidator;

	@RequestMapping(value = "/newbookadded.htm", method = RequestMethod.POST)
	protected String addNewBook(@ModelAttribute("book") Book book, BindingResult result, GenreDAO genreDAO, BookDAO bookDAO, HttpServletRequest request)
			throws IllegalStateException, IOException, BNStoreException {

		bookValidator.validate(book, result);
		
		if(result.hasErrors())
		{
			return "newBook";
		}
		
		String bookName = generateFileName(book.getPhoto());
		File file;
		String check = File.separator;
		String path = null;
		if (check.equalsIgnoreCase("\\")) {
			path = context.getRealPath("").replace("build\\", "");
		}

		if (check.equalsIgnoreCase("/")) {
			path = context.getRealPath("").replace("build/", "");
			path += "/";
		}

		if (book.getPhoto() != null) {
			file = new File(path + "/resources/images/" + bookName);
			book.getPhoto().transferTo(file);

		    book.setPhotoName(bookName);
		}

		Genre genre = genreDAO.get(book.getGenre_name());
		book.setGenre_name(genre.getGenreName());
		book.setGenre(genre);
		genre.addBook(book);
		genreDAO.save(genre);

		try {
			bookDAO.create(book);
		} catch (BNStoreException e) {
			e.printStackTrace();
		}
		return "bookAdded";
	}
	
	@RequestMapping(value = "/newbook.htm", method = RequestMethod.GET)
	public ModelAndView showAddBookForm(@ModelAttribute("book") Book book, GenreDAO genreDAO, BindingResult result)
			throws BNStoreException {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("genreList", genreDAO.list());
		mv.setViewName("newBook");
		return mv;
	}

	private String generateFileName(MultipartFile multiPartFile) {
		return new Date().getTime() + "-" + multiPartFile.getOriginalFilename().replace(" ", "_");
	}
	
	
	@RequestMapping(value="/booklist.htm")
	protected ModelAndView retrieveBooks(HttpServletRequest	 request, BookDAO bookDAO, HttpServletResponse response) throws BNStoreException {
		ModelAndView mv = new ModelAndView();
		List<Book> bookList = bookDAO.getBooks();
		mv.addObject("bookList", bookList);
		mv.setViewName("viewBooks");
		return mv;
		
	}
	
	
	@RequestMapping(value="/displaybooks.htm")
	protected ModelAndView showBooks(HttpServletRequest	 request, BookDAO bookDAO, HttpServletResponse response) throws BNStoreException {
		ModelAndView mv = new ModelAndView();
	//	List<Book> bookList = bookDAO.getBooks();
	//	mv.addObject("bookList", bookList);
		mv.setViewName("displayBooks");
		return mv;	
	}
	
	
	
	@RequestMapping(value="/editbook.htm", method=RequestMethod.GET)
	protected ModelAndView editBook(HttpServletRequest request, HttpServletResponse response, BookDAO bookDAO, GenreDAO genreDAO) throws BNStoreException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		
		long id = Long.parseLong(request.getParameter("id"));
		session.setAttribute("editId", id);
		
		Book book = bookDAO.getBookById(id);
		
		mv.addObject("book", book);
		mv.addObject("genreList", genreDAO.list());
		mv.setViewName("editBook");
		return mv;
		
	}
	
	
	@RequestMapping(value = "/updatebook.htm", method = RequestMethod.POST)
	protected String updateBook(@ModelAttribute("book") Book book, BindingResult result, GenreDAO genreDAO, BookDAO bookDAO, HttpServletRequest request)
			throws IllegalStateException, IOException, BNStoreException {
		//bookValidator.validate(book, result);
		HttpSession session = request.getSession();
		long id =  Long.parseLong(session.getAttribute("editId").toString());
		//long id = Long.parseLong(request.getParameter("id"));
		System.out.print(id);
		//long bookId = Long.parseLong(request.getParameter("id"));
	//	System.out.print(bookId);
	//	System.out.print(request.getParameter("quantity"));
		Book book1 = bookDAO.getBookById(id);
		System.out.print(book.getQuantity());
//		book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		try {
			bookDAO.update(book.getQuantity(), id);
		} catch (BNStoreException e) {
			e.printStackTrace();
		}
		return "redirect:/booklist.htm";
	}
	
	
	@RequestMapping(value = "/deletebook.htm")
	protected String deleteBook(GenreDAO genreDAO, BookDAO bookDAO, HttpServletRequest request, BindingResult result)
			throws IllegalStateException, IOException, BNStoreException {

		long bookId = Long.parseLong(request.getParameter("id"));
		//Book  book = bookDAO.getBookById(id);
		try {
			bookDAO.delete(bookId);
		} catch (BNStoreException e) {
			e.printStackTrace();
		}
		return "redirect:/booklist.htm";
	}
	
	
	@RequestMapping(value = "/goback.htm")
	protected String goBack()
			throws IllegalStateException, IOException, BNStoreException {

		return "customerLoggedIn";
	}
	
}
