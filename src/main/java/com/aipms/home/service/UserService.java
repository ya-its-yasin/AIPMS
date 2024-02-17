package com.aipms.home.service;


import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.aipms.home.model.LoginInfo;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.LoginInfoRepository;

@Service
public interface UserService {
	
	public Optional<UserProfile> getProfile(int id);

	public UserProfile userLogin(UserProfile user);

	public boolean createUser(UserProfile user);

	public boolean forgetPassword(Map secQues);

	public Optional<UserProfile> resetPassword();

	public UserProfile updateProfile(UserProfile user);
	
	
	
}
