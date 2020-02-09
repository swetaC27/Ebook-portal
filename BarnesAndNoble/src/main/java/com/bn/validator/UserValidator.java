package com.bn.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bn.pojo.User;

@Component
public class UserValidator implements Validator {

	@Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.isAssignableFrom(clazz);
    }

	
	@Override
    public void validate(Object obj, Errors errors)
    {
    	User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.username", "Username Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password Required");    
    }


}
