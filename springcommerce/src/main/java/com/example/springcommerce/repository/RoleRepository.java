package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcommerce.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
