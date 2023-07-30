package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.entity.CommentEntity;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	
	public int addComment(int postId, int userId, String content) {
		
		return commentMapper.insertComment(postId, userId, content);
	}
	
	public List<CommentEntity> getAllByOrderByIdDesc() {
		return commentMapper.selectAllByOrderByIdDesc();
	}
	
	// input: postId
	// output: 가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int postId) {
		// 결과 리스트
		List<CommentView> commentViewList = new ArrayList<>();
		
		
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		
		// 반복문 순회 comment => commentView => commentViewList에 담는다.
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 세팅
			commentView.setComment(comment);
			
			// 댓글쓴이 세팅
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user); // 댓글쓴이
			
			// commentViewList에 commentView 리스트 넣기
			commentViewList.add(commentView);
			
		}
		
		return commentViewList;
	}
	
	
	public void deleteCommentById(int commentId) {
		commentMapper.deleteCommentById(commentId);
	}
	
}



