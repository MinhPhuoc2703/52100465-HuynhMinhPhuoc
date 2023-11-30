	package com.example.springcommerce.service;

	import java.util.Arrays;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.stereotype.Service;

	import com.example.springcommerce.entity.Role;
	import com.example.springcommerce.entity.User;
	import com.example.springcommerce.model.UserModel;
	import com.example.springcommerce.repository.RoleRepository;
	import com.example.springcommerce.repository.UserRepository;

	@Service
	public class UserServiceImpl implements UserService {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private RoleRepository roleRepository;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public String registerUser(UserModel userModel) {
			User user = new User();
			user.setUsername(userModel.getUsername());
			user.setEmail(userModel.getEmail());
			user.setPassword(passwordEncoder.encode(userModel.getPassword()));
			Role role = roleRepository.findByName("USER");
			user.setRoles(Arrays.asList(role));
			userRepository.save(user);
			return "success";
		}

		@Override
		public User findByEmail(String email) {
			return userRepository.findByEmail(email);
		}

		@Override
		public User findByUsername(String username) {
			return userRepository.findByUsername(username);
		}


		@Override
		public boolean checkAdmin() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
		}

		@Override
		public User findById(Long id) {
			return userRepository.findById(id).get();
		}

		@Override
		public void save(User user) {
			userRepository.save(user);
		}



	}
