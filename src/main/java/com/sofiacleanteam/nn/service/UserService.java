package com.sofiacleanteam.nn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sofiacleanteam.nn.model.Customer;
import com.sofiacleanteam.nn.model.Role;
import com.sofiacleanteam.nn.model.User;
import com.sofiacleanteam.nn.repository.CustomerRepository;
import com.sofiacleanteam.nn.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Customer registerCustomer(String firstName, String lastName, String username, String password) {

		User user = new User(username, passwordEncoder.encode(password), Role.CUSTOMER, null);
		this.userRepository.save(user);

		Customer customer = new Customer(firstName, lastName, null, user);
		this.customerRepository.save(customer);

		return customer;
	}

	public void registerAdmin(String username, String password) {

		User user = new User(username, passwordEncoder.encode(password), Role.ADMIN, null);
		this.userRepository.save(user);

	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User currentUser = userRepository.findByUsername(userDetails.getUsername());
		
		return currentUser;
	}

}
