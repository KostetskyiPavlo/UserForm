package com.logos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.logos.validator.CheckPassword;
import com.logos.validator.CheckUserEmailExists;
import com.logos.validator.CheckUserLoginExists;

import lombok.Data;

@Data
@CheckPassword
public class UserDTO {

	@NotEmpty(message = "Field FIRST_NAME cant be empty")
	private String firstName;
	
	@NotEmpty(message = "Field LAST_NAME cant be empty")
	private String lastName;
	
	@Email
	@CheckUserEmailExists
	@NotEmpty(message = "Field EMAIL cant be empty")
	private String email;
	
	@CheckUserLoginExists
	@NotEmpty(message = "Field LOGIN cant be empty")
	private String login;

	@Size(min = 8, message = "PASSWORD should contain minimum 8 symbols")
	private String password;
	
	private String passwordConfirm;
	
}
