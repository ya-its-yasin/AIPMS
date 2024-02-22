package com.aipms.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;

@Repository
public interface FloatingRateBondsRepository extends JpaRepository<FloatingRateBonds, Integer>{

	Optional<FloatingRateBonds> findById(int id);

	
} 


