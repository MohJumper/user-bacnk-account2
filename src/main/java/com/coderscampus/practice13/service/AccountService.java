package com.coderscampus.practice13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.practice13.domain.Account;
import com.coderscampus.practice13.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	static
	AccountRepository accountRepo;
	
	public Account save (Account account) {
		return accountRepo.save(account);
	}

	public static Account findById(Long accountId) {
		Optional<Account> userOpt = accountRepo.findById(accountId);
		return userOpt.orElse(new Account());
//		return userOpt;
	}
	
	

}
