<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>

			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 --%>
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none">
				
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일 이름 저장되는 곳 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		<%--// 글쓰기 영역 끝 --%>

		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
		
				<c:forEach items="${postList}" var="post">
				<%-- 카드1 --%>
				<div class="card border rounded mt-3">
					<%-- 글쓴이, 더보기(삭제) --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold">글쓴이${post.userId}</span>
	
						<%-- 더보기 ... --%>
						<a href="#" class="more-btn">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
					</div>
	
					<%-- 카드 이미지 --%>
					<div class="card-img">
						<img src="${post.imagePath}" class="w-100" alt="본문 이미지">
					</div>
	
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<a href="#" class="like-btn">
							<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="filled heart">
						</a>
						좋아요 100개
					</div>
	
					<%-- 글 --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">글쓴이${post.userId}</span>
						<span>${post.content}</span>
					</div>
	
					<%-- 댓글 제목 --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
	
					<%-- 댓글 목록 --%>
					<div class="card-comment-list m-2">
					<c:forEach items="${commentList}" var="comment">
						<c:if test="${comment.postId == post.id}">
							<%-- 댓글 내용들 --%>
							<div class="card-comment m-1">
								<span class="font-weight-bold">댓글쓴이${comment.userId}</span>
								<span>${comment.content}</span>
					
							<%-- 댓글 삭제 버튼 --%>
								<a href="#" class="comment-del-btn">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
								</a>
							</div>
						</c:if>
					</c:forEach>
	
						<%-- 댓글 쓰기 --%>
						<div class="comment-write d-flex border-top mt-2">
							<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
							<button type="button" class="comment-btn btn btn-light" data-post-id="${post.id}">게시</button>
						</div>
					</div> <%--// 댓글 목록 끝 --%>
				</div> <%--// 카드1 끝 --%>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>