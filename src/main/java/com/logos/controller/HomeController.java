package com.logos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.logos.dto.UserDTO;
import com.logos.dto.filter.SimpleFilter;
import com.logos.mapper.UserMapper;
import com.logos.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/add/user")
	public String showAddUserForm(Model model) {
		model.addAttribute("userModel", new UserDTO());
		return "add-user";
	}
	
	@PostMapping("/add/user")
	public String saveUser(
			@Valid @ModelAttribute("userModel") UserDTO user, 
			BindingResult br
			) {
		if(br.hasErrors()) {
			return "add-user";
		}
		userService.saveUser(UserMapper.userDtoToUser(user));
		return "redirect:/";
	}
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("simpleFilterModel", new SimpleFilter());
		model.addAttribute("usersList", userService.findAllUsers());
		return "users-list";
	}
	
	@GetMapping("/users/search")
	public String showUserssByFiler(
			@ModelAttribute("simpleFilterModel") SimpleFilter filter,
			Model model) {
		model.addAttribute("usersList", userService.findAllUsersByFilter(filter));
		
		return "users-list";
	}
	
}
