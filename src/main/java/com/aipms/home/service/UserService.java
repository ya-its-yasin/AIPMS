package com.aipms.home.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aipms.home.model.UserProfile;

@Service
public interface UserService {
	
	public ResponseEntity<?> getProfile(int id);

	public ResponseEntity<?> userLogin(UserProfile user);

	public ResponseEntity<?> createUser(UserProfile user);

	public UserProfile updateProfile(UserProfile user);

	public boolean forgotPassword(String emailId);

	public boolean updatePassword(UserProfile user);
	
}
