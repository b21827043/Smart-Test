package com.brsanlr.brsanlr.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentChapterAnalysis;

public interface StudentChapterAnalysisRepository extends JpaRepository<StudentChapterAnalysis,Long> {
	
	
	
}
