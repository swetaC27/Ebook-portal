package com.bn.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bn.dao.GenreDAO;
import com.bn.exception.BNStoreException;
import com.bn.pojo.Genre;
import com.bn.validator.GenreValidator;

@Controller
public class ManageBooksController {

	@Autowired
	@Qualifier("genreValidator")
	GenreValidator genreValidator;
	

	@RequestMapping(value="/addgenre.htm",method=RequestMethod.GET)
    public String showGenreForm(@ModelAttribute("genre")Genre genre, BindingResult result) { 
   
        return "addGenreForm"; 
    } 
	
	@RequestMapping(value="/genreadded.htm",method=RequestMethod.POST)
    protected String addGenre(@ModelAttribute("genre")Genre genre, BindingResult result, GenreDAO genreDAO) throws Exception
    {
		
		genreValidator.validate(genre, result);
		if(result.hasErrors())
		{
			return "addGenreForm";
		}
        
        try
        {          
        	genreDAO.create(genre);
           
        }
        catch (BNStoreException e)
        {
            System.out.println(e.getMessage());
        }
        return "genreAdded";
    }
	
	
	
}
