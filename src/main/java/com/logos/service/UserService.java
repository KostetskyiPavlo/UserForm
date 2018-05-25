package com.logos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.logos.dto.filter.SimpleFilter;
import com.logos.entity.User;

public interface UserService {

	void saveUser(User user);
	
	User findUserById(int id);
	
	List<User> findAllUsers();
	
	List<User> findAllByFilter(SimpleFilter filter);
	
	Page<User> findUsersByPage(Pageable pageable);
	
	Page<User> findUsersByPageFiltered(Pageable pageable, SimpleFilter filter);

}
