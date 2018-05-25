package com.logos.mapper;

import com.logos.dto.UserDTO;
import com.logos.entity.User;

public interface UserMapper {
	static User userDtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setSalary(userDTO.getSalary());
		user.setEmail(userDTO.getEmail());
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		return user;
		
	}

}
