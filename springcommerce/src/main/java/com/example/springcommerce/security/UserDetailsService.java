package com.example.springcommerce.security;

import com.example.springcommerce.entity.Role;
import com.example.springcommerce.entity.User;
import com.example.springcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findFirstByUsername(username);
		if(user != null) {

			List<Role> roles = user.getRoles();
			Set<GrantedAuthority> ga = new HashSet<>();
			for(Role role : roles) {
				ga.add(new SimpleGrantedAuthority(role.getName()));
			}

			org.springframework.security.core.userdetails.User authUser =
					new org.springframework.security.core.userdetails.User(
					user.getEmail(),
					user.getPassword(),
					ga
					);

			return authUser;
		} else {

			throw new UsernameNotFoundException("Invalid username or password");
		}

	}





}
