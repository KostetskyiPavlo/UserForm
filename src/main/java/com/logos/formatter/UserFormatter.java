package com.logos.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.logos.entity.User;

@Component
public class UserFormatter implements Formatter<User> {

	@Override
	public String print(User object, Locale locale) {
		System.out.println("Print: " + object.getId());
		return String.valueOf(object.getId());
	}

	@Override
	public User parse(String text, Locale locale) throws ParseException {
		System.out.println("Parse: " + text);
		int userId = Integer.valueOf(text);
		
		User user = new User();
		user.setId(userId);
		return null;
	}

}
