package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.StudentDto;
import com.brsanlr.brsanlr.dto.TeacherDto;
import com.brsanlr.brsanlr.dto.UserDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.entities.User;
import com.brsanlr.brsanlr.requests.AddUserRequest;

@Mapper(componentModel="spring",uses= {ExamMapper.class,ClassroomMapper.class,StudentExamStatusMapper.class})
public interface UserMapper {
	
	Student toStudent(AddUserRequest addUserRequest);
	Teacher toTeacher(AddUserRequest addUserRequest);
	
	UserDto toUserDto(User user);
	
	
	List<UserDto> toUserDtoList(List<User> userList);
	
	StudentDto toStudentDto(Student student);
	List<StudentDto> toStudentDto(List<Student> studentList);
	
}
