package com.coderscampus.practice13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.practice13.domain.Address;
import com.coderscampus.practice13.domain.User;
import com.coderscampus.practice13.repository.AddressRepository;
import com.coderscampus.practice13.repository.UserRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private UserRepository userRepo;

//	public Address saveAddress(User user) {
////		User user = new User();
////		Address address = new Address();
////		user.getAddress().setUser(user);
////		user.getAddress().setUserId(user.getUserId());
//		return addressRepo.save(user.getAddress());
//	}

	public Address findById(Long userId) {
		Optional<Address> addressOpt = addressRepo.findById(userId);
		return addressOpt.orElse(new Address());
	}

}
