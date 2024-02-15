package com.aipms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.UserInfo;
import com.aipms.home.service.UserService;

@RestController("/user")
public class UserController {
	
	@Autowired
	UserService service;

	// author -> Murugapandiyan
	@PostMapping("/signup")
	public boolean createUser(@RequestBody UserInfo user)
	{
		return false;
	}
	
	// author -> Radhika
	@PostMapping("/login")
	public boolean userLogin(@RequestBody UserInfo user)
	{
		return false;
	}
	
	// author -> Yasin
	@GetMapping("/profile")
	public UserInfo getProfile(@RequestBody UserInfo user)
	{
		UserInfo result = service.getProfile(user);
		return result;
	}
}
