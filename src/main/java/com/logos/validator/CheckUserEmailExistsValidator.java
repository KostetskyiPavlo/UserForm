package com.logos.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.logos.repository.UserRepository;

@Component
public class CheckUserEmailExistsValidator implements ConstraintValidator<CheckUserEmailExists, String>{

	@Autowired private UserRepository userRepository;

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(userRepository.findUserByEmail(value) == null)
			return true;
		
		return false;
	}

}
