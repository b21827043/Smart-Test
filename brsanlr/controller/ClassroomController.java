package com.brsanlr.brsanlr.controller;

import java.util.List;

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

import com.brsanlr.brsanlr.dto.ClassroomDto;
import com.brsanlr.brsanlr.requests.AddClassroomRequest;
import com.brsanlr.brsanlr.requests.AddExamToClassroomRequest;
import com.brsanlr.brsanlr.requests.UpdateClassroomRequest;
import com.brsanlr.brsanlr.services.ClassroomService;

@RestController
@CrossOrigin(origins="*")
public class ClassroomController {
	
	@Autowired
	ClassroomService classroomService;
	
	
	//---------------  Classroom   ---------------//
	
	//1.Create
	@PostMapping("/classroom")
	public ResponseEntity<ClassroomDto> addClassroom(@RequestBody AddClassroomRequest addClassroomRequest) {
		return new ResponseEntity<>(classroomService.addCourse(addClassroomRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/classroom")
	public ResponseEntity<List<ClassroomDto>> readAllClassroom(){
		return new ResponseEntity<>(classroomService.readAllClassroom(),HttpStatus.OK);
	}
	@GetMapping("/classroom/{id}")
	public ResponseEntity<ClassroomDto> readClassroomByClassroomId(@PathVariable long id){
		ClassroomDto classroomDto = classroomService.readClassroomByClassroomId(id);
		if (classroomDto == null) {
			return new ResponseEntity<>(classroomDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(classroomDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/classroom/id")
	public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable long id,@RequestBody UpdateClassroomRequest updateClassroomRequest){
		ClassroomDto dto = classroomService.updateClassroom(id,updateClassroomRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/classroom/{id}")
	public ResponseEntity<String> deleteClassroom(@PathVariable long id){
		ClassroomDto dto = classroomService.readClassroomByClassroomId(id);
		if (dto == null) {
			return new ResponseEntity<>("Classroom Not Found",HttpStatus.NOT_FOUND);
		}
		classroomService.deleteClassroom(id);
		return new ResponseEntity<>("Classroom Deleted",HttpStatus.OK);
	}
	
	
	//---------------  Classroom-Exam   ---------------//
	
	@PostMapping("/exam/{id}/addClassroom")
	public ResponseEntity<List<ClassroomDto>> addExamToClassroom(@PathVariable long id,@RequestBody AddExamToClassroomRequest addExamToClassRequest){
		List<ClassroomDto> dtoList = classroomService.addExamToClassroom(id,addExamToClassRequest);
		if (dtoList == null) {
			return new ResponseEntity<>(dtoList,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dtoList,HttpStatus.OK);
	}

	
	
	
}
