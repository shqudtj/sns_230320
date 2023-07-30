package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {
	// BO에서 합치면 코드들이 간결해 지고 여러 클래스등을 만들지 않아도 됨 => join문등을 마구잡이로 안 만들어도 됨
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: X
	// output: List<CardView> => CardView는 카드를 나타내기 위한 view를 만들었다고 해줘야 남들도 알 수 있음
	// 비로그인시에도 카드리스트는 뿌려져야 하므로 Integer userId - null 허용
	public List<CardView> generateCardViewList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>(); // []

		// 글 목록 가져온다.
		List<PostEntity> postList = postBO.getPostList(); // => postEntity의 리스트
		
		
		// 글 목록 반복문 순회
		// postEntity => cardView => cardViewList에 담는다.
		for (PostEntity post : postList) {
			// post에 대응되는 하나의 카드를 만든다.
			CardView card = new CardView();
			
			// 글을 세팅한다.
			card.setPost(post);
			
			// 글쓴이를 세팅한다.
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			card.setUser(user);
			
			// 댓글들을 세팅한다.
			List<CommentView> commentViewList = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 개수
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			// 좋아요 여부
			boolean filledLike = likeBO.filledLike(post.getId(), userId);	// 맥락을 읽을 수 있도록 likeBO에서 처리하도록 함
			card.setFilledLike(filledLike);
			
			
			
			// ★★★★★★★★ cardViewList에 담는다.
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}





