package com.brsanlr.brsanlr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brsanlr.brsanlr.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{

}
