package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
				@RequestParam("postId") int postId,
				@RequestParam("content") String content,
				HttpSession session
			) {
		
		// 로그인 정보 가져오기
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 300);
			result.put("errorMessage", "로그인이 필요합니다.");
			return result;
		}
		int rowCount = commentBO.addComment(postId, userId, content);
		if (rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공"); 
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 작성에 실패했습니다.");
		}
		
		
		
		return result;
	}
}
