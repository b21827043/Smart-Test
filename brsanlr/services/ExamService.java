package com.brsanlr.brsanlr.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brsanlr.brsanlr.dto.ExamDto;
import com.brsanlr.brsanlr.dto.StudentExamStatusDto;
import com.brsanlr.brsanlr.entities.Classroom;
import com.brsanlr.brsanlr.entities.Exam;
import com.brsanlr.brsanlr.entities.Question;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentAnswers;
import com.brsanlr.brsanlr.entities.StudentChapterAnalysis;
import com.brsanlr.brsanlr.entities.StudentExamStatus;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.mappers.ExamMapper;
import com.brsanlr.brsanlr.mappers.StudentExamStatusMapper;
import com.brsanlr.brsanlr.repository.ExamRepository;
import com.brsanlr.brsanlr.repository.QuestionRepository;
import com.brsanlr.brsanlr.repository.StudentAnswersRepository;
import com.brsanlr.brsanlr.repository.StudentChapterAnalysisRepository;
import com.brsanlr.brsanlr.repository.StudentExamStatusRepository;
import com.brsanlr.brsanlr.repository.StudentRepository;
import com.brsanlr.brsanlr.repository.TeacherRepository;
import com.brsanlr.brsanlr.requests.AddExamRequest;
import com.brsanlr.brsanlr.requests.ExamEndRequest;
import com.brsanlr.brsanlr.requests.UpdateExamRequest;
import com.brsanlr.brsanlr.responses.ExamStartResponse;

@Service
public class ExamService {
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	StudentExamStatusRepository studentExamStatusRepository;
	@Autowired
	StudentAnswersRepository studentAnswersRepository;
	@Autowired
	StudentExamStatusMapper statusExamStatusMapper;
	@Autowired
	StudentChapterAnalysisRepository studentChapterAnalysisRepository;
	
	
	
	@Autowired
	ExamMapper examMapper;

	
	//---------------  Exam   ---------------//
	
	public ExamDto addExam(AddExamRequest addExamRequest) {
		Exam exam = examMapper.toExam(addExamRequest);
		exam = addQuestionToExam(exam,addExamRequest.getQuestionIdList());
		
		Optional<Teacher> teacher = teacherRepository.findById(addExamRequest.getTeacherId());
		if(teacher.isPresent()) {
			exam.setTeacher(teacher.get());
			
		}
		
		exam = examRepository.save(exam);
		
		teacher.get().addExam(exam);
		
		return examMapper.toExamDto(exam);
	}


	public List<ExamDto> readAllExam() {
		
		List<Exam> examList = examRepository.findAll();

		return examMapper.toExamDtoList(examList);
	}
	


	public ExamDto readExamByExamId(long id) {
		Optional<Exam> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			return examMapper.toExamDto(exam.get());
		}
		return null;
	
	}


	public ExamDto updateExam(long id, UpdateExamRequest updateExamRequest) {
		Optional<Exam> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			Exam updatedExam = examMapper.toExamUpdate(exam.get(), updateExamRequest);
			updatedExam = addQuestionToExam(updatedExam,updateExamRequest.getQuestionIdList());
			updatedExam = examRepository.save(updatedExam);
			return examMapper.toExamDto(updatedExam);
		}
		return null;
	}
	
	public void deleteExam(long id) {
		Optional<Exam> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			Optional<Teacher> teacher = teacherRepository.findById(exam.get().getTeacher().getId());
			if ( teacher.isPresent()) {
				teacher.get().deleteExam(exam.get());
			}
			List<Classroom> classroomList = exam.get().getClassrooms();
			List<Question> questionList = exam.get().getQuestions();
			for (int i = 0 ; i < classroomList.size() ; i++) {
				classroomList.get(i).deleteExam(exam.get());
				exam.get().deleteClassroom(classroomList.get(i));
			}
			for (int i = 0 ; i < questionList.size() ; i++) {
				questionList.get(i).deleteExam(exam.get());
				exam.get().deleteQuestion(questionList.get(i));
			}
			examRepository.deleteById(id);
		}
		
	}
	
	
	//---------------  Exam-Question   ---------------//
	
	public Exam addQuestionToExam(Exam exam,List<Long> questionIdList) {
		
		List<Question> questions = new ArrayList<Question>();
		
		for (int i = 0 ; i < questionIdList.size() ; i++) {
			Optional<Question> question = questionRepository.findById(questionIdList.get(i));
			if (question.isPresent()) {
				questions.add(question.get());
				/*
				if (question.get().getExams().indexOf(exam) == -1) {
					Question foundQuestion = question.get();
					List<Exam> examList = foundQuestion.getExams();
					examList.add(exam);
					foundQuestion.setExams(examList);
					questionRepository.save(foundQuestion);
				}
				*/
			}
		}
		exam.setQuestions(questions);
		return exam;
	}


	
	//---------------  Start Exam   ---------------//
	
	public ExamStartResponse startExam(StudentExamStatus studentExamStatus) {
		
		Optional<Exam> exam = examRepository.findById(studentExamStatus.getExamId());
		if (exam.isPresent()) {
			
			long examDuration = exam.get().getExamDuration();
			
			if (!studentExamStatus.isStart()) {
			
				LocalDateTime startTime = LocalDateTime.now();
				studentExamStatus.setStartDate(startTime);
				
				LocalDateTime examEndTime = startTime.plusMinutes(examDuration);
				
				if (exam.get().getExpiredDate().isAfter(examEndTime)) {
					studentExamStatus.setExamEndDate(examEndTime);
				}
				
				else {
					studentExamStatus.setExamEndDate(exam.get().getExpiredDate());
					examDuration = ChronoUnit.MINUTES.between(exam.get().getExpiredDate(), startTime);
				}
				studentExamStatus.setStart(true);
			}
			else {
				LocalDateTime now = LocalDateTime.now();
				examDuration = ChronoUnit.MINUTES.between(now,studentExamStatus.getExamEndDate());
			}
			studentExamStatusRepository.save(studentExamStatus);
			return new ExamStartResponse(examDuration,examMapper.toExamDto(exam.get()));
		}
		return null;
	} 
	
	//---------------  End Exam   ---------------//
	
	
	public StudentExamStatusDto endExam(StudentExamStatus studentExamStatus,List<ExamEndRequest> examEndRequestList) {
		Optional<Exam> exam = examRepository.findById(studentExamStatus.getExamId());
		if (exam.isPresent() && !studentExamStatus.isSolved()) {
			
			LocalDateTime endTime = LocalDateTime.now();
			if (studentExamStatus.getExamEndDate().isAfter(endTime)) {
				studentExamStatus.setEndDate(endTime);
				studentExamStatus.setSolved(true);
				List<StudentAnswers> studentAnswersList = studentExamStatus.getStudentAnswers();
				
				List<Question> examQuestions = exam.get().getQuestions();
				List<StudentChapterAnalysis> studentChapterAnalysisList = studentExamStatus.getStudent().getStudentChapterAnalysisList();
				for (int i = 0 ; i < examQuestions.size() ; i++) {
					StudentAnswers questionAnswer = new StudentAnswers();
					questionAnswer.setQuestionId(examQuestions.get(i).getQuestionId());
					questionAnswer.setChoiceId(examEndRequestList.get(i).getChoiceId());
					for (int j = 0 ; j < examQuestions.get(i).getChoices().size() ; j++) {
						if ( examQuestions.get(i).getChoices().get(j).isCorrectAnswer() == true) {
							questionAnswer.setTrueChoiceId(examQuestions.get(i).getChoices().get(j).getChoiceId());
						}
					}
					
					long subChapterId = 0;
					Optional<Question> question = questionRepository.findById(questionAnswer.getQuestionId());
					if (question.isPresent()) {
						subChapterId = question.get().getSubChapter().getSubChapterId();
					}
					StudentChapterAnalysis studentChapterAnalysis = null;
					System.out.println(studentChapterAnalysisList);
					for (int z = 0 ; z<studentChapterAnalysisList.size() ; z++) {
						if (subChapterId == studentChapterAnalysisList.get(z).getSubChapterId()) {
							studentChapterAnalysis = studentChapterAnalysisList.get(z);
						}
					}
					
					long trueNumber = studentChapterAnalysis.getTrueNumber();
					long falseNumber = studentChapterAnalysis.getFalseNumber();
					long totalNumber = studentChapterAnalysis.getTotalNumber();
					long trueRate = studentChapterAnalysis.getTrueRate();
					
					
					if (questionAnswer.getChoiceId() == questionAnswer.getTrueChoiceId()) {
						questionAnswer.setTrueChoice(true);
						trueNumber+=1;
						
						
					}
					else {
						falseNumber+=1;
					}
					totalNumber+=1;
					trueRate = totalNumber/totalNumber;
					
					studentChapterAnalysis.setTrueNumber(trueNumber);
					studentChapterAnalysis.setFalseNumber(falseNumber);
					studentChapterAnalysis.setTotalNumber(totalNumber);
					studentChapterAnalysis.setTrueRate(trueRate);
					studentChapterAnalysisRepository.save(studentChapterAnalysis);
					
					
					questionAnswer.setStudentExamStatus(studentExamStatus);
					StudentAnswers savedQuestionAnswer = studentAnswersRepository.save(questionAnswer);
					studentAnswersList.add(savedQuestionAnswer);
				}
				studentExamStatusRepository.save(studentExamStatus);
				
				Optional<StudentExamStatus> returnStudentExamStatus = studentExamStatusRepository.findById(studentExamStatus.getStudentExamStatusId());
				return statusExamStatusMapper.toStudentExamStatusDto(returnStudentExamStatus.get());

			}

		}
		return null;
	}
	
}
