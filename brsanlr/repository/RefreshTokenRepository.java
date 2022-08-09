package com.brsanlr.brsanlr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brsanlr.brsanlr.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
	
	RefreshToken findByUserId(Long userId);
	
}
