package com.brsanlr.brsanlr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brsanlr.brsanlr.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
