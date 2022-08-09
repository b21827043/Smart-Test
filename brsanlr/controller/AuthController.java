package com.brsanlr.brsanlr.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brsanlr.brsanlr.dto.SubChapterDto;
import com.brsanlr.brsanlr.entities.RefreshToken;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.entities.StudentChapterAnalysis;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.entities.User;
import com.brsanlr.brsanlr.jwt.JwtTokenProvider;
import com.brsanlr.brsanlr.mappers.StudentMapper;
import com.brsanlr.brsanlr.mappers.TeacherMapper;
import com.brsanlr.brsanlr.mappers.UserMapper;
import com.brsanlr.brsanlr.repository.StudentChapterAnalysisRepository;
import com.brsanlr.brsanlr.requests.AddUserRequest;
import com.brsanlr.brsanlr.requests.LoginRequest;
import com.brsanlr.brsanlr.requests.RefreshRequest;
import com.brsanlr.brsanlr.responses.AuthResponse;
import com.brsanlr.brsanlr.services.CourseService;
import com.brsanlr.brsanlr.services.RefreshTokenService;
import com.brsanlr.brsanlr.services.UserDetailsServiceImp;
import com.brsanlr.brsanlr.services.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/auth")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	private RefreshTokenService refreshTokenService;
	private UserDetailsServiceImp userDetailsServiceImp;
	
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	CourseService courseService;
	@Autowired
	StudentChapterAnalysisRepository studentChapterAnalysisRepository;
	
    public AuthController(AuthenticationManager authenticationManager, UserService userService, 
    		PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService,UserDetailsServiceImp userDetailsServiceImp) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }
    
    
  //---------------   Login   ---------------//
    
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userService.getOneUserByEmail(loginRequest.getEmail());
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("User successfully login.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return authResponse;
	}
	
	
	
	//---------------   Register   ---------------//
	
	
	@PostMapping("/{author}/register")
	public ResponseEntity<AuthResponse> register(@PathVariable String author,@RequestBody AddUserRequest addUserRequest) {
		
		AuthResponse authResponse = new AuthResponse();
		if(userService.getOneUserByEmail(addUserRequest.getEmail()) != null) {
			authResponse.setMessage("Username already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		
		if (author.equals("student")) {
			Student student = studentMapper.toStudent(addUserRequest);
			student.setPassword(passwordEncoder.encode(student.getPassword()));
			student = userService.saveStudent(student);
			authResponse.setUserId(student.getId());
			authResponse.setRefreshToken(refreshTokenService.createRefreshToken(student));
			
			// Student Chapter Analysis
			
			List<SubChapterDto> subChapterDtoList = courseService.readAllSubChapter();
			//List<StudentChapterAnalysis> studentChapterAnalysisList = new ArrayList<StudentChapterAnalysis>();
			for (int i = 0 ; i < subChapterDtoList.size() ; i++) {
				StudentChapterAnalysis studentChapterAnalysis = new StudentChapterAnalysis(subChapterDtoList.get(i).getSubChapterId(),0,0,0,0,student);
				studentChapterAnalysisRepository.save(studentChapterAnalysis);
			}
			
			
			
			
		}
		if (author.equals("teacher")) {
			Teacher teacher = teacherMapper.toTeacher(addUserRequest);
			teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
			teacher = userService.saveTeacher(teacher);
			authResponse.setUserId(teacher.getId());
			authResponse.setRefreshToken(refreshTokenService.createRefreshToken(teacher));
		}

		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(addUserRequest.getEmail(), addUserRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);		
	}
	
	
	
	
	
	//---------------   Refresh Token   ---------------//
	
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse response = new AuthResponse();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage("token successfully refreshed.");
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);		
		} else {
			response.setMessage("refresh token is not valid.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	//---------------   Teacher Authorization   ---------------//
	


	
	

}