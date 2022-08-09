package com.brsanlr.brsanlr.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brsanlr.brsanlr.dto.AnalysisDto;
import com.brsanlr.brsanlr.dto.StudentAnalysisCourseDto;
import com.brsanlr.brsanlr.dto.StudentAnalysisSubChapterDto;
import com.brsanlr.brsanlr.dto.StudentDto;
import com.brsanlr.brsanlr.dto.StudentTeacherViewDto;
import com.brsanlr.brsanlr.dto.TeacherDto;
import com.brsanlr.brsanlr.dto.UserDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentChapterAnalysis;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.entities.User;
import com.brsanlr.brsanlr.mappers.CourseMapper;
import com.brsanlr.brsanlr.mappers.StudentMapper;
import com.brsanlr.brsanlr.mappers.TeacherMapper;
import com.brsanlr.brsanlr.mappers.UserMapper;
import com.brsanlr.brsanlr.repository.CourseRepository;
import com.brsanlr.brsanlr.repository.StudentChapterAnalysisRepository;
import com.brsanlr.brsanlr.repository.StudentRepository;
import com.brsanlr.brsanlr.repository.TeacherRepository;
import com.brsanlr.brsanlr.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentChapterAnalysisRepository studentChapterAnalysisRepository;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	CourseMapper courseMapper;
	
	public List<UserDto> readAllUser() {
		List<User> userList = userRepository.findAll();
		return userMapper.toUserDtoList(userList);
	}
	
	public List<StudentDto> readAllStudent() {
		List<Student> studentList = studentRepository.findAll();
		return userMapper.toStudentDto(studentList);
	}
	
	
	public List<TeacherDto> readAllTeacher() {
		List<Teacher> teacherList = teacherRepository.findAll();
		return teacherMapper.toTeacherDtoList(teacherList);
	}
	
	
	public User getOneUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> getOneUserById(long id) {
		return userRepository.findById(id);
	}

	public User saveOneUser(User user) {
		return userRepository.save(user);
		
	}
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public Optional<Student> readStudentById(long id) {
		return studentRepository.findById(id);
	}

	public List<StudentAnalysisCourseDto> readStudentAnalysisByStudentId(long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			List<StudentChapterAnalysis> studentChapterAnalysisList = student.get().getStudentChapterAnalysisList();
			List<StudentAnalysisCourseDto> studentAnalysisCourseDto = courseMapper.toStudentAnalysisCourseDtoList(courseRepository.findAll());
			
			for (int i = 0 ; i < studentAnalysisCourseDto.size() ; i++) {
				for (int j = 0 ; j < studentAnalysisCourseDto.get(i).getChapters().size() ; j++) {
					for (int k = 0 ; k < studentAnalysisCourseDto.get(i).getChapters().get(j).getSubChapters().size() ; k++) {
						StudentAnalysisSubChapterDto studentAnalysisSubChapterDto = studentAnalysisCourseDto.get(i).getChapters().get(j).getSubChapters().get(k);
						
						for (int l = 0 ; l < studentChapterAnalysisList.size() ; l++) {
							if (studentChapterAnalysisList.get(l).getSubChapterId() == studentAnalysisSubChapterDto.getSubChapterId()) {
								AnalysisDto analysisDto = new AnalysisDto(studentChapterAnalysisList.get(l).getTrueNumber(),studentChapterAnalysisList.get(l).getFalseNumber(),studentChapterAnalysisList.get(l).getTotalNumber(),studentChapterAnalysisList.get(l).getTrueRate());
								studentAnalysisCourseDto.get(i).getChapters().get(j).getSubChapters().get(k).setAnalysisDto(analysisDto);
							}
						}
						
					}
				}
			}
			return studentAnalysisCourseDto;
		}
		return null;
	}






	

}
