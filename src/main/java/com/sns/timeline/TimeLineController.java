package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.entity.CommentEntity;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

@RequestMapping("/timeline")
@Controller
public class TimeLineController {

	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	
	@RequestMapping("/timeline_view")
	public String timeLineView(Model model,
				HttpSession session
			) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			// 비로그인이면 로그인 페이지로 이동
			return "redirect:/user/sign_in_view";
		}
		
		// DB 글 목록 조회
		List<PostEntity> postList = postBO.getAllByOrderByIdDesc();
		model.addAttribute("postList", postList);
		
				
		// DB 좋아요 댓글 내용 글쓴이 가져오기
		List<CommentEntity> commentList = commentBO.getAllByOrderByIdDesc();
		model.addAttribute("commentList", commentList);
		
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}
