package com.coderscampus.practice13.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.practice13.domain.Account;
import com.coderscampus.practice13.domain.User;
import com.coderscampus.practice13.service.AccountService;
import com.coderscampus.practice13.service.UserService;


@RequestMapping("/users/{userId}/accounts")
public class AccountController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping("{accountId}")
	public String getAnAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		model.put("user", user);
		model.put("account", account);
		return "account";
	}
	
	@PostMapping("/account")
	public String postToCreateAccount (@PathVariable Long userId) {
		Account account = new Account();
		User user = userService.findById(userId);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		account.setAccountName("Account #" + user.getAccounts().size());
		account = accountService.save(account);
		
		return "redirect:/users/"+userId+"/accounts/"+account.getAccountId();
	}
	
	// we want to save a count back to the same account id
	@PostMapping("{accountId}")
	public String saveAccount(Account account, @PathVariable Long userId) {
		account = accountService.save(account);
		return "redirect:/users/"+userId+"/accounts/"+account.getAccountId();
	}
	

}
