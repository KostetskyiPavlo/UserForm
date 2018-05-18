package com.logos.service;

import java.util.List;

import com.logos.dto.filter.SimpleFilter;
import com.logos.entity.User;

public interface UserService {

	void saveUser(User user);
	
	User findUserById(int id);
	
	List<User> findAllUsers();
	
	List<User> findAllUsersByFilter(SimpleFilter filter);

}
