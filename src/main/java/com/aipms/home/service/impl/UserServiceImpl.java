package com.aipms.home.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.aipms.home.model.LoginInfo;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.LoginInfoRepository;
import com.aipms.home.repository.UserProfileRepository;
import com.aipms.home.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserProfileRepository repo;
	
	@Override
	public Optional<UserProfile> getProfile(int id) {
		return repo.findById(id);
	}

	@Override
	public UserProfile userLogin(UserProfile user) {
		UserProfile validUser = repo.findByEmailId(user.getEmailId());
		if(validUser!=null && user.getPassword().equals(validUser.getPassword()))
			return validUser;
		return user;
	}

	// validate and make changes - Radhika
	@Override
	public boolean createUser(UserProfile user) {
		
		repo.save(user);
		
		return true;
	}

	@Override
	public boolean forgotPassword(UserProfile user) {
		// TODO Auto-generated method stub
		UserProfile validUser1 = repo.findByEmailId(user.getEmailId());
		if(validUser1!=null && user.getSecQuestion().equals(validUser1.getSecQuestion()) && user.getSecAnswer().equals(validUser1.getSecAnswer()))
			validUser1.setPassword(validUser1.getPassword());
		    repo.save(validUser1);
		return true;	
		 
	}



	@Override
	public UserProfile updateProfile(UserProfile user) {	
		UserProfile us = getProfile(user.getUserId()).get();
		us.setAadharNumber(user.getAadharNumber());
		us.setAddress(user.getAddress());
		us.setAge(user.getAge());
		us.setDOB(user.getDOB());
		us.setGender(user.getGender());
		us.setNationality(user.getNationality());
		us.setNomineeName(user.getNomineeName());
		us.setPanNumber(user.getPanNumber());
		us.setSecQuestion(user.getSecAnswer());
		us.setSecAnswer(user.getSecAnswer());
		repo.save(us);
		return us;
	}
}
