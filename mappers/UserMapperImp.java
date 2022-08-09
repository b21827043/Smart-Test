package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.StudentDto;
import com.brsanlr.brsanlr.dto.TeacherDto;
import com.brsanlr.brsanlr.dto.UserDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.entities.User;
import com.brsanlr.brsanlr.requests.AddUserRequest;

@Component
public class UserMapperImp implements UserMapper{
	
	@Autowired
	ExamMapper examMapper;
	@Autowired
	ClassroomMapper classroomMapper;
	@Autowired
	StudentExamStatusMapper studentExamStatusMapper;
	
	
	@Override
	public Student toStudent(AddUserRequest addUserRequest) {
		return new Student(addUserRequest.getEmail(),addUserRequest.getFullName(),addUserRequest.getPassword());
	}

	@Override
	public Teacher toTeacher(AddUserRequest addUserRequest) {
		return new Teacher(addUserRequest.getEmail(),addUserRequest.getFullName(),addUserRequest.getPassword());
	}
	


	@Override
	public UserDto toUserDto(User user) {
		return new UserDto(user.getId(),user.getEmail(),user.getFullName(),user.getImgUrl());
	}

	
	

	@Override
	public List<UserDto> toUserDtoList(List<User> userList) {
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		if (userList == null) {
			return userDtoList;
		}
		for (int i = 0 ; i < userList.size() ; i++) {
			UserDto userDto = toUserDto(userList.get(i));
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public StudentDto toStudentDto(Student student) {
		return new StudentDto(student.getId(),student.getEmail(),student.getFullName(),student.getImgUrl(),studentExamStatusMapper.toStudentExamStatusDtoList(student.getExamStatusList()),classroomMapper.toClassroomStudentViewDtoList(student.getClassrooms()));
	}

	@Override
	public List<StudentDto> toStudentDto(List<Student> studentList) {
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		if ( studentList == null) {
			return studentDtoList;
		}
		for (int i = 0 ; i < studentList.size(); i++) {
			StudentDto studentDto = toStudentDto(studentList.get(i));
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}







}
