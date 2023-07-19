package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.dao.PostMapper;
import com.sns.post.dao.PostRepository;
import com.sns.post.entity.PostEntity;

@Service
public class PostBO {

	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	// 로그인된 아이디의 post리스트를 가져와야함
	
	public List<PostEntity> getAllByOrderByIdDesc() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
}
