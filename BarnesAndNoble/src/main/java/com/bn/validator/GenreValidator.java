package com.bn.validator;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bn.dao.GenreDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Customer;
import com.bn.pojo.Genre;

import org.springframework.validation.ValidationUtils;

@Component
public class GenreValidator implements Validator {

	@Override
    public boolean supports(Class clazz)
    {
		return Genre.class.isAssignableFrom(clazz);
    }

	@Override
    public void validate(Object obj, Errors errors)
    {
    	Genre genre = (Genre) obj;
    	
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genreName", "error.genreName", "Genre Required");

    	GenreDAO genreDao = new GenreDAO();
		List<Genre> gen;
		try {
			gen = genreDao.list();
			for (Genre g : gen){
				if(g.getGenreName().equalsIgnoreCase(genre.getGenreName())){
					errors.rejectValue("genreName", "genreName.alreadyExist","This Genre already exists!");
				}
			}
			
			
		} catch (BNStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
