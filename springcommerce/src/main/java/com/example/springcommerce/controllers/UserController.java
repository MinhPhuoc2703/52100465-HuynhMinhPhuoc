package com.example.springcommerce.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springcommerce.entity.User;
import com.example.springcommerce.model.UserModel;
import com.example.springcommerce.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public ModelAndView signUpForm() {
		ModelAndView mav = new ModelAndView("sign-up-form");
		UserModel userModel = new UserModel();
		mav.addObject("userModel", userModel);
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView("login-form");
		UserModel userModel = new UserModel();
		mav.addObject("userModel", userModel);
		return mav;
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid @ModelAttribute UserModel userModel,  
			BindingResult result) {
		User existingUsername = userService.findByUsername(userModel.getUsername());
		User existingEmail = userService.findByEmail(userModel.getEmail());
		if(existingUsername != null || existingEmail!=null) {
			return "redirect:/users/signup?existing";
		}
		
		if(result.hasErrors()) {
			return "redirect:/users/signup?invalid";
		} 
		
		if(!userModel.getPassword().contentEquals(userModel.getRepeatPassword())) {
			return "redirect:/users/signup?password";
		}
		userService.registerUser(userModel);
		return "redirect:/home?success";
		
	}
}
