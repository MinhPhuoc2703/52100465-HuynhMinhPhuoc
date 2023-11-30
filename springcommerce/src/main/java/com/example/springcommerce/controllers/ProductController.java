package com.example.springcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import com.example.springcommerce.entity.User;
import com.example.springcommerce.entity.Cart;
import com.example.springcommerce.entity.Product;
import com.example.springcommerce.model.Charge;
import com.example.springcommerce.repository.ProductRepository;
import com.example.springcommerce.repository.CartRepository;
import com.example.springcommerce.service.UserService;
import com.example.springcommerce.security.SecurityUtil;
@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserService userService;

	@Autowired
	CartRepository cartRepository;


	@GetMapping("/all_products")
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView("show-products");
		String email = SecurityUtil.getSessionUser();
		User user = userService.findByEmail(email);
		if (user != null) {
			boolean isAdmin = userService.checkAdmin();
			mav.addObject("isadmin", isAdmin);
		}
		List<Product> allProducts = productRepository.findAll();
		mav.addObject("allProducts", allProducts);
		mav.addObject("user", user);
		return mav;
	}

	@GetMapping("/cart")
	public String showCart(Model model) {
		String email = SecurityUtil.getSessionUser();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);

		double totalCost = 0;
		for (Product product : user.getCart().getProducts()) {
			totalCost += product.getPrice();
		}
		model.addAttribute("totalcost", totalCost);
		return "cart";
	}

	@GetMapping("/cart/add")
	public String addCart(@RequestParam Long productid) {
		String email = SecurityUtil.getSessionUser();
		Cart cart = userService.findByEmail(email).getCart();
		Product product = productRepository.findById(productid).get();
		List<Product> newProductList = cart.getProducts();
		newProductList.add(product);
		cart.setProducts(newProductList);
		cartRepository.save(cart);
		return "redirect:/products/all_products";
	}

	@GetMapping("/cart/remove")
	public String removeCart(@RequestParam Long productid) {
		String email = SecurityUtil.getSessionUser();
		Cart cart = userService.findByEmail(email).getCart();
		Product product = productRepository.findById(productid).get();
		List<Product> newProductList = cart.getProducts();
		newProductList.remove(product);
		cart.setProducts(newProductList);
		cartRepository.save(cart);
		return "redirect:/products/cart?removed";
	}



	@GetMapping("/cart/payment")
	public String paymentForm(Model model) {
		String email = SecurityUtil.getSessionUser();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		int totalCost = 0;
		for (Product product : user.getCart().getProducts()) {
			totalCost += product.getPrice();
		}
		model.addAttribute("amount", totalCost);
		model.addAttribute("currency", Charge.Currency.VND);
		return "payment";
	}



	@GetMapping("/details/")
	public String showProductDetails(@RequestParam Long productid, Model model) {
		Product product = productRepository.findById(productid).get();
		model.addAttribute("product", product);
		return "product-details";

	}



	@GetMapping("/search")
	public ModelAndView searchProducts(@RequestParam("keyword") String keyword) {
		ModelAndView mav = new ModelAndView("show-products");
		String email = SecurityUtil.getSessionUser();
		User user = userService.findByEmail(email);

		if (user != null) {
			boolean isAdmin = userService.checkAdmin();
			mav.addObject("isadmin", isAdmin);
		}

		List<Product> searchResults = productRepository.findByNameContainingIgnoreCase(keyword);
		mav.addObject("allProducts", searchResults);
		mav.addObject("user", user);

		return mav;
	}



	@GetMapping("/filter")
	public ModelAndView filterProductsByPrice(@RequestParam("priceRange") String priceRange) {
		String[] prices = priceRange.split("-");
		int minPrice = Integer.parseInt(prices[0]);
		int maxPrice = Integer.parseInt(prices[1]);

		List<Product> filteredProducts = productRepository.findByPriceBetween(minPrice, maxPrice);

		ModelAndView mav = new ModelAndView("show-products");
		mav.addObject("allProducts", filteredProducts);


		return mav;
	}


	@PostMapping("/order")
	public ModelAndView placeOrder(@RequestParam("fullName") String fullName,
								   @RequestParam("address") String address,
								   @RequestParam("phoneNumber") String phoneNumber,
								   @RequestParam("email") String email,
								   @RequestParam("amount") Double amount) {
		ModelAndView mav = new ModelAndView("order-success");
		mav.addObject("fullName", fullName);
		mav.addObject("email", email);
		mav.addObject("address", address);
		mav.addObject("phonenumber", phoneNumber);
		mav.addObject("amount", amount);
		return mav;
	}








}
