package com.aipms.home.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aipms.home.controller.UserController;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.UserProfileRepository;
import com.aipms.home.service.UserService;
import com.aipms.home.util.EncryptionUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserProfileRepository repo;
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	OTPEmailServiceImpl emailService;
	 
	@Autowired
	EncryptionUtils encyptionUtil;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public ResponseEntity<?> getProfile(int id) {
		Optional<UserProfile> user = repo.findById(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>("{ \"body\" : \"User does not exist\" }",HttpStatus.BAD_REQUEST );
		}else {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> userLogin(UserProfile user) {
		UserProfile validUser = repo.findByEmailId(user.getEmailId());
		if(validUser!=null) {
			String frontendDecryptedPassword = decodePassword(user.getPassword());
			String backendDecryptedPassword = encyptionUtil.decrypt(validUser.getPassword());
			if(backendDecryptedPassword.equals(frontendDecryptedPassword))
				return new ResponseEntity<>(validUser,HttpStatus.OK);
		}
		return new ResponseEntity<>("{ \"body\" : \"Email or password is incorrect\"}", HttpStatus.UNAUTHORIZED);
	}
	
	private String decodePassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }
	
	@Override
	public ResponseEntity<?> createUser(UserProfile user) {	
		if(repo.findByEmailId(user.getEmailId()) != null) {
			return new ResponseEntity<>("{ \"body\" : \"Email ID already exist\"}",HttpStatus.BAD_REQUEST );
		}
		String frontendDecryptedPassword = decodePassword(user.getPassword());
		String backendEncryptedPassword = encyptionUtil.encrypt(frontendDecryptedPassword);
		System.out.println(user.getPassword() + " " + frontendDecryptedPassword + " " + backendEncryptedPassword);
		user.setPassword(backendEncryptedPassword);
		repo.save(user);
		
		return new ResponseEntity<>("{ \"body\" : \"User registered successfully\"}", HttpStatus.OK);
	}

	@Override
	public boolean forgotPassword(String emailId) {	
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    int otp = Integer.parseInt(String.format("%06d", number));
	    UserProfile exist = repo.findByEmailId(emailId);	
	    exist.setTempOTP(otp);
	    repo.save(exist);
		return emailService.sendSimpleMail(otp, emailId);
	}

	@Override
	public UserProfile updateProfile(UserProfile user) {	
		UserProfile us = repo.findById(user.getUserId()).get();
		us.setAadharNumber(user.getAadharNumber());
		us.setAddress(user.getAddress());
		us.setAge(user.getAge());
		us.setDOB(user.getDOB());
		us.setGender(user.getGender());
		us.setNationality(user.getNationality());
		us.setNomineeName(user.getNomineeName());
		us.setPanNumber(user.getPanNumber());
		us.setWalletBalance(user.getWalletBalance());
		repo.save(us);
		return us;
	}

	@Override
	public boolean updatePassword(UserProfile user) {
		UserProfile validUser = repo.findByEmailId(user.getEmailId());
		if(user.getTempOTP() == validUser.getTempOTP() ) {
			String frontendDecryptedPassword = decodePassword(user.getPassword());
			String backendEncryptedPassword = encyptionUtil.encrypt(frontendDecryptedPassword);
			validUser.setPassword(backendEncryptedPassword);
			validUser.setTempOTP(0);
			repo.save(validUser);
			logger.warn("Password has been changed by the user -> " + user.getEmailId());
			System.out.println(user.getPassword() + " " + frontendDecryptedPassword + " " + backendEncryptedPassword);
			return true;
		}
		return false;
	}
	
}
