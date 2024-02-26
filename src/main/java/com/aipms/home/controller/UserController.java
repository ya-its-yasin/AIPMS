package com.aipms.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.UserProfile;
import com.aipms.home.service.UserService;

@CrossOrigin(origins = "http://localhost:4200") //port number of angular application
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public String apiCheck()
	{
		logger.info("tested check endpoint");
		return "I'm working";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody UserProfile user)
	{
		return service.createUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody UserProfile user)
	{
		return service.userLogin(user);
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<?> getProfile(@PathVariable int id)
	{
		return service.getProfile(id);
	}
	
	@PutMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody UserProfile user)
	{
		service.forgotPassword(user.getEmailId());
		return new ResponseEntity<>("OTP to change password has been sent through mail", HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UserProfile user)
	{
		if(service.updatePassword(user)) {
			return new ResponseEntity<>("Password successfully changed", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Password change attempt failed, please try again by generating new OTP", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProfile(@RequestBody UserProfile user)
	{
		return new ResponseEntity<>(service.updateProfile(user), HttpStatus.OK);
	}
}
