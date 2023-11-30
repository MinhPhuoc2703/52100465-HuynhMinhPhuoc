package com.example.springcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy="cart")
	private User user;
	
	@ManyToMany
	@JoinTable(name="carts_products",
			joinColumns= {@JoinColumn(name="cart_id", referencedColumnName="id")},
			inverseJoinColumns= {@JoinColumn(name="product_id", referencedColumnName="id")})
	private List<Product> products = new ArrayList<>();
}
