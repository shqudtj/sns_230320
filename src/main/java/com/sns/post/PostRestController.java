package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
				@RequestParam("writeTextArea") String writeTextArea
				, @RequestParam("file") MultipartFile file
				, HttpSession session
			) {
		
		// 로그인 정보 받아오기
		int userId = (int)session.getAttribute("userId");
		// 많은 접속자 있는 곳이면 Integer해야 오류가 안나지만 지금은 나중에 총괄체크할거기 때문에 ㄱㅊ
		String userLoginId = (String)session.getAttribute("userLoginId"); 
		
		
		// db insert
		postBO.addPost(userId, userLoginId, writeTextArea, file);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		
		return result;
	}
}
