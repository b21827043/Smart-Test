package com.brsanlr.brsanlr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brsanlr.brsanlr.dto.ClassroomDto;
import com.brsanlr.brsanlr.entities.Classroom;
import com.brsanlr.brsanlr.entities.Exam;
import com.brsanlr.brsanlr.entities.Post;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentExamStatus;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.mappers.ClassroomMapper;
import com.brsanlr.brsanlr.repository.ClassroomRepository;
import com.brsanlr.brsanlr.repository.ExamRepository;
import com.brsanlr.brsanlr.repository.PostRepository;
import com.brsanlr.brsanlr.repository.StudentExamStatusRepository;
import com.brsanlr.brsanlr.repository.StudentRepository;
import com.brsanlr.brsanlr.repository.TeacherRepository;
import com.brsanlr.brsanlr.repository.UserRepository;
import com.brsanlr.brsanlr.requests.AddClassroomRequest;
import com.brsanlr.brsanlr.requests.AddExamToClassroomRequest;
import com.brsanlr.brsanlr.requests.UpdateClassroomRequest;

@Service
public class ClassroomService {
	
	@Autowired
	ClassroomRepository classroomRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	StudentExamStatusRepository studentExamStatusRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	PostRepository postRepository;
	
	
	@Autowired
	ClassroomMapper classroomMapper;
	
	
	
	
	//---------------  Classroom   ---------------//
	
	
	public ClassroomDto addCourse(AddClassroomRequest addClassroomRequest) {
		Optional<Teacher> teacher = teacherRepository.findById(addClassroomRequest.getTeacherId());
		Classroom classroom = classroomMapper.requestToClassroom(addClassroomRequest);
		classroom = addStudentToClassroom(classroom,addClassroomRequest.getStudentIdList());
		
		if (teacher.isPresent()) {
			classroom.setTeacher(teacher.get());
		}
		
		
		classroom = classroomRepository.save(classroom);

		return classroomMapper.toClassroomDto(classroom);
	}

	public List<ClassroomDto> readAllClassroom() {
		List<Classroom> classroomList = classroomRepository.findAll();

		
		return classroomMapper.toClassroomDtoList(classroomList);
	}

	public ClassroomDto readClassroomByClassroomId(long id) {
		Optional<Classroom> classroom = classroomRepository.findById(id);
		if (classroom.isPresent()) {
			return classroomMapper.toClassroomDto(classroom.get());
		}
		return null;
	}

	public ClassroomDto updateClassroom(Long id,UpdateClassroomRequest updateClassroomRequest) {
		Optional<Classroom> classroom = classroomRepository.findById(id);
		if (classroom.isPresent()) {
			Classroom updatedClassroom = classroomRepository.save(classroomMapper.toClassroomUpdate(classroom.get(), updateClassroomRequest));
			return classroomMapper.toClassroomDto(updatedClassroom);
		}
		return null;
	}

	public void deleteClassroom(long id) {
		Optional<Classroom> classroom = classroomRepository.findById(id);
		
		if (classroom.isPresent()) {
			
			Classroom foundClassroom = classroom.get();
			
			foundClassroom.getTeacher().deleteClassroom(foundClassroom);
			foundClassroom.deleteTeacher();
			
			
			List<Post> postList = foundClassroom.getPosts();
			List<Student> userList = foundClassroom.getStudents();
			List<Exam> examList = foundClassroom.getExams();
			
			for (int i = 0 ; i<userList.size() ; i++) {
				userList.get(i).deleteClassroom(foundClassroom);
				studentRepository.save(userList.get(i));
				
			}
			for(int i = 0 ; i<examList.size();i++) {
				examList.get(i).deleteClassroom(foundClassroom);
				examRepository.save(examList.get(i));
			}
			for (int i = 0 ; i <postList.size() ; i++) {
				postList.get(i).deleteClassroom();
				postRepository.save(postList.get(i));
				postRepository.deleteById(postList.get(i).getPostId());
			}
			
			foundClassroom.setStudents(null);
			foundClassroom.setExams(null);
			foundClassroom.setPosts(null);
			
			classroomRepository.delete(foundClassroom);
		}
		
	}
	
	//---------------  Classroom-Student   ---------------//
	
	public Classroom addStudentToClassroom(Classroom classroom, List<Long> studentIdList) {
		
		List<Student> students = new ArrayList<Student>();
		
		for (int i = 0 ; i < studentIdList.size() ; i++) {
			Optional<Student> student = studentRepository.findById(studentIdList.get(i));
			if (student.isPresent()) {
				students.add(student.get());
				/*
				if(student.get().getClassrooms().indexOf(classroom) == -1) {
					List<Classroom> classroomList = student.get().getClassrooms();
					classroomList.add(classroom);
					Student foundStudent = student.get();
					foundStudent.setClassrooms(classroomList);
					//studentRepository.save(foundStudent);
				}
				*/
			}
			
		}
		
		classroom.setStudents(students);
		return classroom;

	}

	//---------------  Classroom-Exam   ---------------//
	
	
	public List<ClassroomDto> addExamToClassroom(long id,AddExamToClassroomRequest addExamToClassRequest) {
		List<Classroom> classroomList = new ArrayList<Classroom>();
		Optional<Exam> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			List<Long> classroomIdList = addExamToClassRequest.getClassroomIdList();
			for (int i = 0 ; i < classroomIdList.size() ; i++ ) {
				Optional<Classroom> classroom = classroomRepository.findById(classroomIdList.get(i));
				if (classroom.isPresent()) {
					Classroom foundClassroom = classroom.get();
					List<Exam> classroomExamList = foundClassroom.getExams();
					
					if (classroomExamList.indexOf(exam.get()) == -1) {
						
						classroomExamList.add(exam.get());
						foundClassroom.setExams(classroomExamList);
						classroomRepository.save(foundClassroom);
						
						List<Student> classroomUserList = classroom.get().getStudents();
						for (int j = 0 ; j < classroomUserList.size() ; j++) {
							StudentExamStatus studentExamStatus = new StudentExamStatus(id);
							studentExamStatus.setStudent(classroomUserList.get(j));
							studentExamStatusRepository.save(studentExamStatus);
						}
						
					}
					classroomList.add(foundClassroom);
				}
				
			}
			
			return classroomMapper.toClassroomDtoList(classroomList);
			
		}

		return null;
	}
	
	
	
	
	
	
	
	
	
}
