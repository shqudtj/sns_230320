package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.comon.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	// 로그인된 아이디의 post리스트를 가져와야함
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public PostEntity addPost(int userId, String userLoginId, String writeTextArea, MultipartFile file) {
		
		String imagePath = null;
		
		if (file != null) {
			imagePath = fileManagerService.saverFile(userLoginId, file);
		}
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(writeTextArea)
				.imagePath(imagePath)
				.build());
	}
	
	public void deletePostByPostIdUserId(int postId,  int userId) {
		// 기존글 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		if (post == null) {
			logger.error("[delete post] postId:{}, userId:{}", postId, userId);
			return;
		}
		
		// 이미지 있으면 이미지 삭제
		fileManagerService.deleteFile(post.getImagePath());

		// 글삭제
		postRepository.delete(post);

		// 댓글들 삭제
		commentBO.deleteCommentsByPostId(postId);

		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
	}
}
