package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.StudentTeacherViewDto;
import com.brsanlr.brsanlr.dto.UserDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.requests.AddUserRequest;

@Mapper(componentModel = "spring", uses= {StudentExamStatusMapper.class,ClassroomMapper.class})
public interface StudentMapper {
	
	Student toStudent(AddUserRequest addUserRequest);
	StudentTeacherViewDto toStudentDto(Student student);
	
	UserDto studentToUserDto(Student student);
	List<UserDto> studentListToUserDtoList(List<Student> userList);
	
	List<StudentTeacherViewDto> toStudentDtoList(List<Student> studentList);
	
}
