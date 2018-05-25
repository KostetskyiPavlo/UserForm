package com.logos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.logos.dto.UserDTO;
import com.logos.dto.filter.SimpleFilter;
import com.logos.entity.User;
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
	
//	@GetMapping("/users")
//	public String showUsers(Model model) {
//		model.addAttribute("simpleFilterModel", new SimpleFilter());
//		model.addAttribute("usersList", userService.findAllUsers());
//		return "users-list";
//	}
	
    @GetMapping("/users")
    public String showUsersPage(Model model) {
        model.addAttribute("usersList", userService.findAllUsers());
        return "users-list";
    }

    @GetMapping("/users/pages")
    public String showUsersByPage(
            @PageableDefault Pageable pageable,
            Model model
    ) {
        Page<User> page = userService.findUsersByPage(pageable);
        int currentPage = page.getNumber();
        int begin = Math.max(1, currentPage - 5);
        int end = Math.min(begin + 5, page.getNumber());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", currentPage);
        model.addAttribute("usersList", page);
        model.addAttribute("usersPageListByPageSize", page.getContent());
        model.addAttribute("searchText", new SimpleFilter());
        return "users-list";
    }

    @GetMapping("/users/pages/search")
    public String showUserFiltered(
            @ModelAttribute("searchText") SimpleFilter filter,
            @PageableDefault Pageable pageable,
            Model model
    ) {
        Page<User> page = userService.findUsersByPageFiltered(pageable, filter);
        int currentPage = page.getNumber();
        int begin = Math.max(1, currentPage - 5);
        int end = Math.min(begin + 5, currentPage);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", currentPage);
        model.addAttribute("usersList", page);
        model.addAttribute("usersPageListByPageSize", page.getContent());
        model.addAttribute("searchText", filter);
        return "users-list";
    }
}
