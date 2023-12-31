package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.domain.Comment;
import com.sns.comment.entity.CommentEntity;

@Repository
public interface CommentMapper {

	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	public List<CommentEntity> selectAllByOrderByIdDesc();
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int commentId);
	
	public void deleteCommentsByPostId(int postId);
}
