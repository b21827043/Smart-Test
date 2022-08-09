package com.brsanlr.brsanlr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brsanlr.brsanlr.dto.ExamDto;
import com.brsanlr.brsanlr.dto.StudentExamStatusDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentExamStatus;
import com.brsanlr.brsanlr.requests.AddExamRequest;
import com.brsanlr.brsanlr.requests.ExamEndRequest;
import com.brsanlr.brsanlr.requests.UpdateExamRequest;
import com.brsanlr.brsanlr.responses.ExamStartResponse;
import com.brsanlr.brsanlr.services.ExamService;
import com.brsanlr.brsanlr.services.UserService;

@RestController
@CrossOrigin(origins="*")
public class ExamController {
	
	@Autowired
	ExamService examService;
	@Autowired 
	UserService userService;
	
	//---------------  Exam   ---------------//
	
	//1.Create
	@PostMapping("/exam")
	public ResponseEntity<ExamDto> addExam(@RequestBody AddExamRequest addExamRequest) {
		return new ResponseEntity<>(examService.addExam(addExamRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/exam")
	public ResponseEntity<List<ExamDto>> readAllExam(){
		return new ResponseEntity<>(examService.readAllExam(),HttpStatus.OK);
	}
	@GetMapping("/exam/{id}")
	public ResponseEntity<ExamDto> readExamByExamId(@PathVariable long id){
		ExamDto examDto = examService.readExamByExamId(id);
		if (examDto == null) {
			return new ResponseEntity<>(examDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(examDto,HttpStatus.OK);
	}
	
	// 3. Update
	@PutMapping("/exam/id")
	public ResponseEntity<ExamDto> updateExam(@PathVariable long id,@RequestBody UpdateExamRequest updateExamRequest){
		ExamDto dto = examService.updateExam(id,updateExamRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/exam/{id}")
	public ResponseEntity<String> deleteExam(@PathVariable long id){
		ExamDto dto = examService.readExamByExamId(id);
		if (dto == null) {
			return new ResponseEntity<>("Classroom Not Found",HttpStatus.NOT_FOUND);
		}
		examService.deleteExam(id);
		return new ResponseEntity<>("Classroom Deleted",HttpStatus.OK);
	}
	
	
	
	//// Exam Start
	@PostMapping("student/{studentId}/exam/{examId}/start")
	public ResponseEntity<ExamStartResponse> startExam(@PathVariable long studentId,@PathVariable long examId){
		ExamStartResponse examStartResponse = new ExamStartResponse();
		Optional<Student> student = userService.readStudentById(studentId);
		if (student.isPresent()) {
			List<StudentExamStatus> examStatusList = student.get().getExamStatusList();
			for (int i = 0 ; i < examStatusList.size() ; i++) {
				if (examStatusList.get(i).getExamId() == examId ) {
					examStartResponse = examService.startExam(examStatusList.get(i));
					if (examStartResponse != null) {
						return new ResponseEntity<>(examStartResponse,HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<>(examStartResponse,HttpStatus.NOT_FOUND);
	}
		
	

	//// Exam End 
	@PostMapping("student/{studentId}/exam/{examId}/end")
	public ResponseEntity<StudentExamStatusDto> endExam(@PathVariable long studentId,@PathVariable long examId,@RequestBody List<ExamEndRequest> examEndRequestList) {
		Optional<Student> student = userService.readStudentById(studentId);
		if (student.isPresent()) {
			List<StudentExamStatus> examStatusList = student.get().getExamStatusList();
			for (int i = 0 ; i < examStatusList.size() ; i++) {
				if (examStatusList.get(i).getExamId() == examId ) {
					StudentExamStatusDto studentExamStatusDto = examService.endExam(examStatusList.get(i),examEndRequestList);
					if ( studentExamStatusDto != null) {
						return new ResponseEntity<>(studentExamStatusDto,HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	
	
}
