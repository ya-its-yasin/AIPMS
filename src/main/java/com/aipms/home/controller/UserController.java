package com.aipms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.UserProfile;
import com.aipms.home.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;

	@GetMapping
	public String apiCheck()
	{
		return "I'm working";
	}
	
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
	public UserProfile getProfile(@PathVariable int id)
	{
		return service.getProfile(id);
	}
	
	@PutMapping("/forgotpassword")
	public boolean forgotPassword(@RequestBody UserProfile user)
	{
		return service.forgotPassword(user);
	}
	
	@PutMapping("/update")
	public UserProfile updateProfile(@RequestBody UserProfile user)
	{
		return service.updateProfile(user);
	}
}
