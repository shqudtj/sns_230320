package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comon.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserResetController {

	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
				@RequestParam("loginId") String loginId
			) {
		
		Map<String, Object> result = new HashMap<>();
		
		// select
		UserEntity userEntity = userBO.getUserEntityByLoginId(loginId);
		
		if (userEntity != null) {
			result.put("code", 1);
			result.put("isDuplicatedId", true);
		} else {
			result.put("code", 1);
			result.put("isDuplicatedId", false);
		}
		
		return result;
		
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		// 비밀번호 해싱 - md5 알고리즘 ( 취약한 알고리즘이지만 쉬워서 지금만 사용 다른 거 할 시에 딴거 해보기)
		// aaaa => 74b8733745420d4d33f80c4663dc5e5 라면
		// aaaa => 74b8733745420d4d33f80c4663dc5e5 으로 같은 값입력시 같은 암호가 나와서 보안에 취약함
		String hashedPassword = EncryptUtils.md5(password);
		
		// db insert
		Integer userId = userBO.addUser(loginId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			result.put("code" , 1);
			result.put("result" , "성공");
		} else {
			result.put("code" , 500);
			result.put("errorMessage" , "회원가입 하는데 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("sign_in")
	public Map<String, Object> signIn(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				HttpServletRequest request
			) {
	
		// passwordhashing
		String hashedPassword = EncryptUtils.md5(password);
		
		// loginId, hashedPassord로 userEntity채우기
		UserEntity userEntity = userBO.getUserEntityByLoginIdAndPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (userEntity != null) {
			// 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("uesrId", userEntity.getId());
			session.setAttribute("uesrLoginId", userEntity.getLoginId());
			session.setAttribute("uesrName", userEntity.getName());
			
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 로그인 불가
			
			result.put("code", 500);
			result.put("result", "존재하지 않느 사용자입니다.");
		}
		
		return result;
	}
	
	
	
}
