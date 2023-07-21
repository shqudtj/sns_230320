package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.entity.CommentEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(int postId, int userId, String content) {
		
		return commentMapper.insertComment(postId, userId, content);
	}
	
	public List<CommentEntity> getAllByOrderByIdDesc() {
		return commentMapper.selectAllByOrderByIdDesc();
	}
}
