package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long > {
	
	User findByEmail(String email);
	
	User findByUsername(String username);

	User findFirstByUsername(String username);
	
}
