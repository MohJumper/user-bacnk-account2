package com.coderscampus.practice13.web;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.practice13.domain.User;
import com.coderscampus.practice13.service.AccountService;
import com.coderscampus.practice13.service.AddressService;
import com.coderscampus.practice13.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountSerice;
	
	@Autowired
	private AddressService addressService;
	
	
	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		
		model.put("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String postCreateUser (User user) {
		System.out.println(user);
		userService.saveUser(user);
		return "redirect:/users";
	}
	
	@GetMapping("/users")
	public String getAllUsers (ModelMap model) {
		Set<User> users = userService.findAll();
		
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		
		return "users";
	}
	

	
	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
//		Address address = addressSerivce.findById(userId);
		model.put("users", Arrays.asList(user));
		model.put("user", user);
//		model.put("address", user.getAddress());
		return "users";
	}
	
	
	
	// ---------------------------
	@PostMapping("/users/{userId}")
	public String postOneUser (User user) {
		user.getAddress().setUser(user);
		user.getAddress().setUserId(user.getUserId());
//		addressService.saveAddress(user);
		userService.saveUser(user);
		
		// TODO we need a way to save address 
//		addressSerivce.saveAddress(user);
		return "redirect:/users/"+ user.getUserId();
	}
	
	@PostMapping("/users/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}
	
	
}
