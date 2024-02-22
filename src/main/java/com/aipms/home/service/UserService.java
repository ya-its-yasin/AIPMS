package com.aipms.home.service;


import org.springframework.stereotype.Service;

import com.aipms.home.model.UserProfile;

@Service
public interface UserService {
	
	public UserProfile getProfile(int id);

	public UserProfile userLogin(UserProfile user);

	public boolean createUser(UserProfile user);

	public UserProfile updateProfile(UserProfile user);

	public boolean forgotPassword(UserProfile user);
	
}
