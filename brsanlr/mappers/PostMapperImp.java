package com.brsanlr.brsanlr.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.PostDto;
import com.brsanlr.brsanlr.entities.Post;
import com.brsanlr.brsanlr.requests.AddPostRequest;
import com.brsanlr.brsanlr.requests.UpdatePostRequest;



@Component
public class PostMapperImp implements PostMapper{
	
	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public Post toPost(AddPostRequest addPostRequest) {
		LocalDateTime now = LocalDateTime.now();
		return new Post(addPostRequest.getSenderId(),addPostRequest.getPostTitle(),addPostRequest.getPostText(),now);
	}

	@Override
	public PostDto toPostDto(Post post) {
		return new PostDto(post.getPostId(),post.getSenderId(),post.getPostTitle(),post.getPostText(),post.getPostDate(),messageMapper.toMessageDtoList(post.getMessages()));
	}

	@Override
	public List<PostDto> toPostDtoList(List<Post> postList) {
		List<PostDto> postDtoList = new ArrayList<PostDto>();
		if (postList == null) {
			return postDtoList;
		}
		for (int i = 0 ; i<postList.size() ; i++) {
			PostDto postDto = toPostDto(postList.get(i));
			postDtoList.add(postDto);
		}
		return postDtoList;
	}

	@Override
	public Post toPostUpdate(Post post,UpdatePostRequest updatePostRequest) {
		return new Post(post.getPostId(),post.getSenderId(),updatePostRequest.getPostTitle(),updatePostRequest.getPostText(),post.getPostDate());
	} 

}
