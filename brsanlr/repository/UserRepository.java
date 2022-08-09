package com.brsanlr.brsanlr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brsanlr.brsanlr.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String username);
	
}
