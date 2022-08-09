package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.PostDto;
import com.brsanlr.brsanlr.entities.Post;
import com.brsanlr.brsanlr.requests.AddPostRequest;
import com.brsanlr.brsanlr.requests.UpdatePostRequest;

@Mapper(componentModel = "spring" , uses=MessageMapper.class)
public interface PostMapper {
	
	Post toPost(AddPostRequest addPostRequest);
	PostDto toPostDto(Post post);
	List<PostDto> toPostDtoList(List<Post> postList);
	Post toPostUpdate(Post post,UpdatePostRequest updatePostRequest);
}
