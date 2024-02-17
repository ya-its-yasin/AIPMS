package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

	UserProfile findByEmailId(String emailId);

}
