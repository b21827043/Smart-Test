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

import com.brsanlr.brsanlr.dto.MessageDto;
import com.brsanlr.brsanlr.dto.PostDto;
import com.brsanlr.brsanlr.requests.AddMessageRequest;
import com.brsanlr.brsanlr.requests.AddPostRequest;
import com.brsanlr.brsanlr.requests.UpdateMessageRequest;
import com.brsanlr.brsanlr.requests.UpdatePostRequest;
import com.brsanlr.brsanlr.services.ForumService;

@RestController
@CrossOrigin(origins="*")
public class ForumController {
	
	@Autowired
	ForumService forumService;
	
	
	//---------------   POST   ---------------//	
	
	// 1. Create
	@PostMapping("/post")
	public ResponseEntity<PostDto> addPost(@RequestBody AddPostRequest addPostRequest) {
		return new ResponseEntity<>(forumService.addPost(addPostRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> readAllPost(){
		return new ResponseEntity<>(forumService.readAllPost(),HttpStatus.OK);
	}
	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> readPostByPostId(@PathVariable long id){
		PostDto postDto = forumService.readPostByPostId(id);
		if (postDto == null) {
			return new ResponseEntity<>(postDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(postDto,HttpStatus.OK);
	}

	
	// 3. Update 
	@PutMapping("/post/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable long id,@RequestBody UpdatePostRequest updatePostRequest){
		PostDto dto = forumService.updatePost(id,updatePostRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	
	// 4. Delete
	@DeleteMapping("/post/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id){
		PostDto postDto = forumService.readPostByPostId(id);
		if (postDto == null) {
			return new ResponseEntity<>("Post Not Found",HttpStatus.NOT_FOUND);
		}
		forumService.deletePost(id);
		return new ResponseEntity<>("Post Deleted",HttpStatus.OK);
	}
	
	
	//---------------   Message   ---------------//
	
	// 1. Create
	@PostMapping("/message")
	public ResponseEntity<MessageDto> addMessage(@RequestBody AddMessageRequest addMessageRequest) {
		return new ResponseEntity<>(forumService.addMessage(addMessageRequest),HttpStatus.CREATED);
	}
	
	// 2. Read
	@GetMapping("/message")
	public ResponseEntity<List<MessageDto>> readAllMessage(){
		return new ResponseEntity<>(forumService.readAllMessage(),HttpStatus.OK);
	}
	
	@GetMapping("/message/{id}")
	public ResponseEntity<MessageDto> readMessageByMessageId(@PathVariable long id){
		MessageDto messageDto = forumService.readMessageByMessageId(id);
		if (messageDto == null) {
			return new ResponseEntity<>(messageDto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(messageDto,HttpStatus.OK);
	}
	
	// 3. Update 
	@PutMapping("/message/{id}")
	public ResponseEntity<MessageDto> updateMessage(@PathVariable long id,@RequestBody UpdateMessageRequest updateMessageRequest){
		MessageDto dto = forumService.updateMessage(id,updateMessageRequest);
		if (dto == null) {
			return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/message/{id}")
	public ResponseEntity<String> deleteMessage(@PathVariable long id){
		MessageDto messageDto = forumService.readMessageByMessageId(id);
		if (messageDto == null) {
			return new ResponseEntity<>("Message Not Found",HttpStatus.NOT_FOUND);
		}
		forumService.deleteMessage(id);
		return new ResponseEntity<>("Message Deleted",HttpStatus.OK);
	}
	
}
