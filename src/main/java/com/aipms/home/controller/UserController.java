package com.aipms.home.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.LoginInfo;
import com.aipms.home.model.UserProfile;
import com.aipms.home.service.UserService;
import com.aipms.home.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl service;

	@GetMapping
	public String apiCheck()
	{
		return "I'm working";
	}
	
	// radika
	@PostMapping("/signup")
	public boolean createUser(@RequestBody UserProfile user)
	{
		return service.createUser(user);
	}
	
	@PostMapping("/login")
	public UserProfile userLogin(@RequestBody UserProfile user)
	{
		return service.userLogin(user);
	}
	
	@GetMapping("/profile/{id}")
	public Optional<UserProfile> getProfile(@PathVariable int id)
	{
		return service.getProfile(id);
	}
	
	@GetMapping("/reset-password")
	public Optional<UserProfile> resetPassword()
	{
		return service.resetPassword();
	}
	
	@PutMapping("/update")
	public UserProfile updateProfile(@RequestBody UserProfile user)
	{
		return service.updateProfile(user);
	}
}
