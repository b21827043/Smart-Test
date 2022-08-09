package com.brsanlr.brsanlr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.brsanlr.brsanlr.dto.MessageDto;
import com.brsanlr.brsanlr.dto.PostDto;
import com.brsanlr.brsanlr.entities.Classroom;
import com.brsanlr.brsanlr.entities.Message;
import com.brsanlr.brsanlr.entities.Post;
import com.brsanlr.brsanlr.mappers.MessageMapper;
import com.brsanlr.brsanlr.mappers.PostMapper;
import com.brsanlr.brsanlr.repository.ClassroomRepository;
import com.brsanlr.brsanlr.repository.MessageRepository;
import com.brsanlr.brsanlr.repository.PostRepository;
import com.brsanlr.brsanlr.requests.AddMessageRequest;
import com.brsanlr.brsanlr.requests.AddPostRequest;
import com.brsanlr.brsanlr.requests.UpdateMessageRequest;
import com.brsanlr.brsanlr.requests.UpdatePostRequest;

@Service
public class ForumService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostMapper postMapper;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageMapper messageMapper;
	
	@Autowired 
	ClassroomRepository classroomRepository;
	
	
	//---------------   POST   ---------------//

	public PostDto addPost(AddPostRequest addPostRequest) {
		Post post = postMapper.toPost(addPostRequest);
		Optional<Classroom> classroom = classroomRepository.findById(addPostRequest.getClassroomId());
		if ( classroom.isPresent() ) {
			post.setClassroom(classroom.get());
		}
		return postMapper.toPostDto(postRepository.save(post));
	}


	public List<PostDto> readAllPost() {
		List<Post> postList = postRepository.findAll();
		return postMapper.toPostDtoList(postList);
	}


	public PostDto readPostByPostId(long id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			return postMapper.toPostDto(post.get());
		}
		return null;
	}

	

	public PostDto updatePost(long id, UpdatePostRequest updatePostRequest) {
		Optional<Post> oldPost = postRepository.findById(id);
		if (oldPost.isPresent()) {
			Post post = postMapper.toPostUpdate(oldPost.get(),updatePostRequest);
			post.setMessages(oldPost.get().getMessages());
			Post updatedPost = postRepository.save(post);
			return postMapper.toPostDto(updatedPost);
		}
		return null;
	}


	public void deletePost(long id) {
		postRepository.deleteById(id);
	}
	
	
	//---------------   Message   ---------------//

	public MessageDto addMessage(AddMessageRequest addMessageRequest) {
		Message message = messageMapper.toMessage(addMessageRequest);
		if (postRepository.findById(addMessageRequest.getPostId()).isPresent()) {
			message.setPost(postRepository.findById(addMessageRequest.getPostId()).get());
		}
		message = messageRepository.save(message);
		return messageMapper.toMessageDto(message);
	}

	public List<MessageDto> readAllMessage() {
		List<Message> messageList = messageRepository.findAll();
		return messageMapper.toMessageDtoList(messageList);
	}


	public MessageDto readMessageByMessageId(long id) {
		Optional<Message> message = messageRepository.findById(id);
		if (message.isPresent()) {
			return messageMapper.toMessageDto(message.get());
		}
		return null;
	}


	public MessageDto updateMessage(long id,UpdateMessageRequest updateMessageRequest) {
		Optional<Message> oldMessage = messageRepository.findById(id);
		if (oldMessage.isPresent()) {
			Message message = messageMapper.toMessageUpdate(oldMessage.get(),updateMessageRequest);
			message.setPost(oldMessage.get().getPost());
			Message updatedMessage = messageRepository.save(message);
			return messageMapper.toMessageDto(updatedMessage);
		}
		return null;
	}


	public void deleteMessage(long id) {
		messageRepository.deleteById(id);
	}
	

}
