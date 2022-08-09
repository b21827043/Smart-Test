package com.brsanlr.brsanlr.dto;


import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	private long postId;
	
	private long senderId;
	
	private String postTitle;
	private String postText;
	
	private LocalDateTime postDate;
	
	
	private List<MessageDto> messages;
	
	

	
	
	
	
}
