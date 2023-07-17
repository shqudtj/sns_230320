<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="justify-content-center">
	<h2>회원가입</h2>
	<form id="signUpForm" method="post" action="/user/sign_up">
		<div class="sigUpBox">
			<div>ID</div>
			<div>
				<input type="text" name="loginId" class="form-control" placeholder="id를 입력해주세요.">
				<button id="loginIdCheckBtn"  type="button" class="btn bg-primary text-white">중복확인</button>	
			</div>
				<%-- 아이디 체크 결과 --%>
				<%-- d-none 클래스: display none (보이지 않게) --%>
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
				
			<div>password</div>
			<input type="password" name="password" placeholder="****" class="form-control">
			<div>passwordConfirm</div>
			<input type="password" name="passwordConfirm" placeholder="****" class="form-control">
			<div>이름</div>
			<input type="text" name="name" placeholder="이름을 입력해주세요" class="form-control">
			<div>이메일</div>
			<input type="text" placeholder="이름을 입력해주세요" class="form-control">
			<button type="submit" name="email" class="btn btn-primary text-white mt-2">가입하기</button>
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//alert("준비");
		
		$("#loginIdCheckBtn").on('click', function() {
			// alert("클릭");
			
			// 경고 문구 초기화
			$('#idCheckLength').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			
			let loginId = $('#loginId').val().trim();
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass('d-none');
				return;
			}
			
			// AJAX 통신 - 중복확인
			$.ajax({
				// request
				url:"/user/is_duplicated_id"
				, data: {"loginId":loginId}
				
				// response
				, success: function(data) {
					if (data.isDuplicatedId) {
						// 중복
						$('#idCheckDuplicated').removeClass('d-none');
					} else {
						// 중복아님 => 사용 가능
						$('#idCheckOk').removeClass('d-none');
					}			
				}
				, error: function(request, status, error) {
					alert('중복확인에 실패했습니다.');
				}
				
				
			});
			
		});
		
		
		
		
		
		
	});




</script>