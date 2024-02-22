package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.LoginInfo;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Integer> {

	LoginInfo findByEmailId(String email);
	
	
}
