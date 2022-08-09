package com.brsanlr.brsanlr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brsanlr.brsanlr.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
