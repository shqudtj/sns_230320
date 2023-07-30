package com.sns.like.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {

	// ctrl alt h 눌러서 해당하는 클래스를 사용하는지 확인 후 교체
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId
//			);
	
	//public int selectLikeCountByPostId(int postId);
	
	// by PostId userId, by postId => 하나의 쿼리로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId
			);
	
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId
			);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId
			);

	public void deleteLikeByPostId(int postId);
	
}



