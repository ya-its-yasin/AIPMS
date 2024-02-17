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
	public boolean forgetPassword(Map secQues) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<UserProfile> resetPassword() {
		// TODO Auto-generated method stub
		return Optional.empty();
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
		repo.save(us);
		return us;
	}
}
