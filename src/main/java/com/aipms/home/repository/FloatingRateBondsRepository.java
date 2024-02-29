package com.aipms.home.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.UserProfile;

@Repository
public interface FloatingRateBondsRepository extends JpaRepository<FloatingRateBonds, Integer>{

	List<FloatingRateBonds> findByFrbHolder(UserProfile frbHolder);

	
} 


