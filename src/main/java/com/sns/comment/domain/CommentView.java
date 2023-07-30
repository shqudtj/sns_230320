package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
// 댓글 하나. comment와 1:1 매핑
public class CommentView {

	// 댓글 하나
	private Comment comment;
	
	// 댓글쓴이
	private UserEntity user;
	
	
}
