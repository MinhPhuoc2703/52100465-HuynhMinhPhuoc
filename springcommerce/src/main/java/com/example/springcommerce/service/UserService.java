package com.example.springcommerce.service;

import com.example.springcommerce.entity.User;
import com.example.springcommerce.model.UserModel;


public interface UserService {
	public String registerUser(UserModel userModel);
	
	public User findByEmail(String email);
	
	public User findById(Long id);
	
	public User findByUsername(String username);
	
	public void save(User user);
	
	public boolean checkAdmin();


}
