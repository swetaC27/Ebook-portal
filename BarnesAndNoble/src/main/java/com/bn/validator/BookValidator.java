package com.bn.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.bn.dao.BookDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Book;

@Component
public class BookValidator implements Validator {

	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

	public boolean supports(Class aClass) {
		return aClass.equals(Book.class);
	}

	public void validate(Object obj, Errors errors) {

		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher;
		MultipartFile photo;

		Book book = (Book) obj;

		BookDAO bookDao = new BookDAO();
		List<Book> bk;
		try {
			bk = bookDao.getBooks();
			for (Book b : bk) {
				if (b.getIsbn().equals(book.getIsbn())) {

					errors.rejectValue("isbn", "isbn.alreadyExist", "This isbn already exists!");
				}
			}

		} catch (BNStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "error.isbn", "ISBN Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "Title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "error.author", "Author Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.price", "Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre_name", "error.genre_name",
				"Genre Name Required. Create at least one Genre.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.quantity", "Quantity Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photoName", "error.photoName",
//				"Photo Name Required");

		if(book.getQuantity()<=0) {
			errors.rejectValue("quantity", "error.quantity", "Enter valid quatity");
		}
		
		if(book.getPrice()<=0 || book.getPrice()<=0.0) {
			errors.rejectValue("price", "error.price", "Enter valid amount");
		}
		
		photo = book.getPhoto();

		matcher = pattern.matcher(photo.getOriginalFilename());
		// matching with the original file again

//		if (0 == photo.getSize()) {
//			errors.rejectValue("photo", "Test", "File is empty");
//		}
//		if (!matcher.matches()) {
//			errors.rejectValue("photo", "Test", "Invalid Image Format");
//		}

//		if (5000000 < photo.getSize()) {
//			errors.rejectValue("photo", "Test", "File size is over 5mb!");
//		}

	}
}
