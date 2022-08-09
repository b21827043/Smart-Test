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

import com.brsanlr.brsanlr.dto.ChapterDto;
import com.brsanlr.brsanlr.dto.ChoiceDto;
import com.brsanlr.brsanlr.dto.CourseDto;
import com.brsanlr.brsanlr.dto.QuestionDto;
import com.brsanlr.brsanlr.dto.SubChapterDto;
import com.brsanlr.brsanlr.requests.AddChapterRequest;
import com.brsanlr.brsanlr.requests.AddChoiceRequest;
import com.brsanlr.brsanlr.requests.AddQuestionRequest;
import com.brsanlr.brsanlr.requests.AddSubChapterRequest;
import com.brsanlr.brsanlr.requests.CourseRequest;
import com.brsanlr.brsanlr.requests.UpdateChapterRequest;
import com.brsanlr.brsanlr.requests.UpdateChoiceRequest;
import com.brsanlr.brsanlr.requests.UpdateQuestionRequest;
import com.brsanlr.brsanlr.requests.UpdateSubChapterRequest;
import com.brsanlr.brsanlr.services.CourseService;

@RestController
@CrossOrigin(origins="*")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	
	//---------------   Course   ---------------//
	
	//1.Create
	@PostMapping("/course")
	public ResponseEntity<CourseDto> addCourse(@RequestBody CourseRequest courseRequest) {
		return new ResponseEntity<>(courseService.addCourse(courseRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/course")
	public ResponseEntity<List<CourseDto>> readAllCourse(){
		return new ResponseEntity<>(courseService.readAllCourse(),HttpStatus.OK);
	}
	@GetMapping("/course/{id}")
	public ResponseEntity<CourseDto> readCourseByCourseId(@PathVariable long id){
		CourseDto courseDto = courseService.readCourseByCourseId(id);
		if (courseDto == null) {
			return new ResponseEntity<>(courseDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(courseDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/course/{id}")
	public ResponseEntity<CourseDto> updateCourse(@PathVariable long id,@RequestBody CourseRequest courseRequest){
		CourseDto dto = courseService.updateCourse(id,courseRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable long id){
		CourseDto dto = courseService.readCourseByCourseId(id);
		if (dto == null) {
			return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
		}
		courseService.deleteCourse(id);
		return new ResponseEntity<>("Course Deleted",HttpStatus.OK);
	}
	
	//---------------   Chapter   ---------------//
	
	//1.Create
	@PostMapping("/chapter")
	public ResponseEntity<ChapterDto> addChapter(@RequestBody AddChapterRequest addChapterRequest) {
		return new ResponseEntity<>(courseService.addChapter(addChapterRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/chapter")
	public ResponseEntity<List<ChapterDto>> readAllChapter(){
		return new ResponseEntity<>(courseService.readAllChapter(),HttpStatus.OK);
	}
	@GetMapping("/chapter/{id}")
	public ResponseEntity<ChapterDto> readChapterByChapterId(@PathVariable long id){
		ChapterDto chapterDto = courseService.readChapterByChapterId(id);
		if (chapterDto == null) {
			return new ResponseEntity<>(chapterDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(chapterDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/chapter/{id}")
	public ResponseEntity<ChapterDto> updateChapter(@PathVariable long id,@RequestBody UpdateChapterRequest updateChapterRequest){
		ChapterDto dto = courseService.updateChapter(id,updateChapterRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/chapter/{id}")
	public ResponseEntity<String> deleteChapter(@PathVariable long id){
		ChapterDto dto = courseService.readChapterByChapterId(id);
		if (dto == null) {
			return new ResponseEntity<>("Chapter Not Found",HttpStatus.NOT_FOUND);
		}
		courseService.deleteChapter(id);
		return new ResponseEntity<>("Chapter Deleted",HttpStatus.OK);
	}
	
	//---------------   SubChapter   ---------------//
	
	//1.Create
	@PostMapping("/subChapter")
	public ResponseEntity<SubChapterDto> addSubChapter(@RequestBody AddSubChapterRequest addSubChapterRequest) {
		return new ResponseEntity<>(courseService.addSubChapter(addSubChapterRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/subChapter")
	public ResponseEntity<List<SubChapterDto>> readAllSubChapter(){
		return new ResponseEntity<>(courseService.readAllSubChapter(),HttpStatus.OK);
	}
	@GetMapping("/subChapter/{id}")
	public ResponseEntity<SubChapterDto> readSubChapterBySubChapterId(@PathVariable long id){
		SubChapterDto subChapterDto = courseService.readSubChapterBySubChapterId(id);
		if (subChapterDto == null) {
			return new ResponseEntity<>(subChapterDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(subChapterDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/subChapter/{id}")
	public ResponseEntity<SubChapterDto> updateSubChapter(@PathVariable long id,@RequestBody UpdateSubChapterRequest updateSubChapterRequest){
		SubChapterDto dto = courseService.updateSubChapter(id,updateSubChapterRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/subChapter/{id}")
	public ResponseEntity<String> deleteSubChapter(@PathVariable long id){
		SubChapterDto dto = courseService.readSubChapterBySubChapterId(id);
		if (dto == null) {
			return new ResponseEntity<>("SubChapter Not Found",HttpStatus.NOT_FOUND);
		}
		courseService.deleteSubChapter(id);
		return new ResponseEntity<>("SubChapter Deleted",HttpStatus.OK);
	}
	
	//---------------   Question   ---------------//
	
	//1.Create
	@PostMapping("/question")
	public ResponseEntity<QuestionDto> addQuestion(@RequestBody AddQuestionRequest addQuestionRequest) {
		return new ResponseEntity<>(courseService.addQuestion(addQuestionRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/question")
	public ResponseEntity<List<QuestionDto>> readAllQuestion(){
		return new ResponseEntity<>(courseService.readAllQuestion(),HttpStatus.OK);
	}
	@GetMapping("/question/{id}")
	public ResponseEntity<QuestionDto> readQuestionByQuestionId(@PathVariable long id){
		QuestionDto questionDto = courseService.readQuestionByQuestionId(id);
		if (questionDto == null) {
			return new ResponseEntity<>(questionDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(questionDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/question/{id}")
	public ResponseEntity<QuestionDto> updateQuestion(@PathVariable long id,@RequestBody UpdateQuestionRequest updateQuestionRequest){
		QuestionDto dto = courseService.updateQuestion(id,updateQuestionRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/question/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable long id){
		QuestionDto dto = courseService.readQuestionByQuestionId(id);
		if (dto == null) {
			return new ResponseEntity<>("Question Not Found",HttpStatus.NOT_FOUND);
		}
		courseService.deleteQuestion(id);
		return new ResponseEntity<>("Question Deleted",HttpStatus.OK);
	}
	//---------------   Choice   ---------------//
	
	//1.Create
	@PostMapping("/choice")
	public ResponseEntity<ChoiceDto> addChoice(@RequestBody AddChoiceRequest addChoiceRequest) {
		return new ResponseEntity<>(courseService.addChoice(addChoiceRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/choice")
	public ResponseEntity<List<ChoiceDto>> readAllChoice(){
		return new ResponseEntity<>(courseService.readAllChoice(),HttpStatus.OK);
	}
	@GetMapping("/choice/{id}")
	public ResponseEntity<ChoiceDto> readChoiceByChoiceId(@PathVariable long id){
		ChoiceDto choiceDto = courseService.readChoiceByChoiceId(id);
		if (choiceDto == null) {
			return new ResponseEntity<>(choiceDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(choiceDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/choice/{id}")
	public ResponseEntity<ChoiceDto> updateChoice(@PathVariable long id,@RequestBody UpdateChoiceRequest updateChoiceRequest){
		ChoiceDto dto = courseService.updateChoice(id,updateChoiceRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	// 4. Delete
	@DeleteMapping("/choice/{id}")
	public ResponseEntity<String> deleteChoice(@PathVariable long id){
		ChoiceDto dto = courseService.readChoiceByChoiceId(id);
		if (dto == null) {
			return new ResponseEntity<>("Choice Not Found",HttpStatus.NOT_FOUND);
		}
		courseService.deleteChoice(id);
		return new ResponseEntity<>("Choice Deleted",HttpStatus.OK);
	}
}
