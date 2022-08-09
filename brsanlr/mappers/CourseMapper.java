package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.CourseDto;
import com.brsanlr.brsanlr.dto.StudentAnalysisCourseDto;
import com.brsanlr.brsanlr.entities.Course;
import com.brsanlr.brsanlr.requests.CourseRequest;

@Mapper(componentModel = "spring",uses=ChapterMapper.class)
public interface CourseMapper {
	
	Course toCourse(CourseRequest courseRequest);
	CourseDto toCourseDto(Course course);
	List<CourseDto> toCourseDtoList(List<Course> courseList);
	
	Course toCourseUpdate(Course course,CourseRequest courseRequest);
	
	StudentAnalysisCourseDto toStudentAnalysisCourseDto(Course course);
	List<StudentAnalysisCourseDto> toStudentAnalysisCourseDtoList(List<Course> courseList);
	
}
