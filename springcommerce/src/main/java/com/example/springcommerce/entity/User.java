package com.example.springcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String username;
	private String password;
	
	private String address;
	private String tel;
	

	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(
			name="users_roles",
			joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id") },
			inverseJoinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")}
			)
	private List<Role> roles = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id", referencedColumnName="id")
	private Cart cart = new Cart();
	



}
