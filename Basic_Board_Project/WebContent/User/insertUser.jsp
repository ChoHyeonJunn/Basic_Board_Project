<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="inc_header.html" %>

<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>

<!-- javaScript library load -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script type="text/javascript" src="resources/js/RSA/rsa.js"></script>
<script type="text/javascript" src="resources/js/RSA/jsbn.js"></script>
<script type="text/javascript" src="resources/js/RSA/prng4.js"></script>
<script type="text/javascript" src="resources/js/RSA/rng.js"></script>

	<script type="text/javascript">

		$(function() {
			var rsa = new RSAKey();
			rsa.setPublic("${modulus}", "${exponent}");

			$("#insertForm").submit(function(e) {
				
				e.preventDefault();
				// 실제 유저 입력 form은 event 취소
				// javaScript가 작동되지 않는 환경에서는 유저 입력 form이 submit됨 -> server 측에서 검증되므로 로그인 불가.
				
				var USERID = $(this).find("#USERID").val();
				var PASSWORD = $(this).find("#PASSWORD").val();
				var NAME = $(this).find("#NAME").val();

				$("#hiddenForm input[name='USERID']").val(rsa.encrypt(USERID));
				$("#hiddenForm input[name='PASSWORD']").val(rsa.encrypt(PASSWORD));
				$("#hiddenForm input[name='NAME']").val(rsa.encrypt(NAME));

				// 임시 출력 alert!!//////////////////////////////////////
				alert("userid : " + $("#hiddenForm input[name='USERID']").val() + "\n" 
						+ "password : " + $("#hiddenForm input[name='PASSWORD']").val() + "\n"
						+ "name : " + $("#hiddenForm input[name='NAME']").val() + "\n");
				//////////////////////////////////////////////////////
				
				$("#hiddenForm").submit();
			})
		})
	</script>

</head>

<body>
	<div class="container mx-auto mt-5 p-5 shadow" style="width: 50%">
		<a href="javascript:history.go(-1)" class="btn btn-info">돌아가기</a>
		<P>
		<H2>USER :: 회원가입</H2>
		<HR>
		
		<!-- 사용자에게 입력받는 form -->
		<form id=insertForm name=form1 method=post action="/Basic_Board_Project/UserController?action=insert">
			<input type=hidden name="action" value="insert">
			<div class="form-group">
				<label for="USERID">ID : </label>
			    <input type="text" class="form-control" id="USERID" name="USERID" placeholder="아이디를 입력해주세요" maxlength="10" autofocus autocomplete="off" required />
			<div class="check_font" id="id_check"></div>	<!-- 경고문이 들어갈 공간 -->
			</div>

			<div class="form-group">
				<label for="PASSWORD">PW : </label>
			    <input type="password" class="form-control" id="PASSWORD" name="PASSWORD" placeholder="비밀번호를 입력해주세요" autocomplete="off" required />
			</div>

			<div class="form-group">
				<label for="NAME">NAME : </label>
				<input type="text" class="form-control" id="NAME" name="NAME" placeholder="이름을 입력해주세요" autocomplete="off" required/>
			</div>

			<button type="submit" class="btn btn-primary">가입</button>
			<a class="btn btn-dark" href="javascript:history.go(-1)">취소</a><p>
		</form>
		
		<!-- 실제 서버로 전송되는 FORM -->
		<form action="/Basic_Board_Project/UserController?action=insert" method="post" id="hiddenForm">
			<fieldset>
				<input type="hidden" name="USERID">
				<input type="hidden" name="PASSWORD">
				<input type="hidden" name="NAME">
			</fieldset>
		</form>
		
	</div>
</body>
</html>