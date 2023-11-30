package com.example.springcommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.springcommerce.entity.User;
import com.example.springcommerce.service.UserService;
import com.example.springcommerce.security.SecurityUtil;
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping({"/", "/home"})
	public String home(Model model) {
		String email = SecurityUtil.getSessionUser();
		System.out.println(email);
		if(email != null) {
			User user = userService.findByEmail(email);
			model.addAttribute("user", user);
			boolean isAdmin = userService.checkAdmin();
			model.addAttribute("isadmin", isAdmin);
		}
		return "home";
	}
}
