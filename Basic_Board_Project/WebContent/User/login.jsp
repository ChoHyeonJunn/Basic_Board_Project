<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc_header.html" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
	<!-- 순서에 유의 -->
	<script type="text/javascript" src="resources/js/RSA/rsa.js"></script>
	<script type="text/javascript" src="resources/js/RSA/jsbn.js"></script>
	<script type="text/javascript" src="resources/js/RSA/prng4.js"></script>
	<script type="text/javascript" src="resources/js/RSA/rng.js"></script>

	<script type="text/javascript">
	
		$(function() {
			
			var $USERID = $("#hiddenForm input[name='USERID']");
	    	var $PASSWORD = $("#hiddenForm input[name='PASSWORD']");
			
			// 서버로부터 받은 공개키 입력
			var rsa = new RSAKey();
			rsa.setPublic("${modulus}","${exponent}");
			
			$("#loginForm").submit(function(e) {
		    	
		    	// 실제 입력 FORM 이벤트는 취소
		    	e.preventDefault();
		    	
		    	var USERID = $(this).find("#USERID").val();
		    	var PASSWORD = $(this).find("#PASSWORD").val();
		    	
		    	// 아이디, 비밀번호 암호화
		    	$USERID.val(rsa.encrypt(USERID));
		    	$PASSWORD.val(rsa.encrypt(PASSWORD));
		    	
		    	$("#hiddenForm").submit();
		    })
		})
	
	</script>

</head>
<body>

	<div class="container mx-auto mt-5 p-5 shadow" style="width: 50%">
		<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
		<H2>USER :: 로그인</H2>
		
		<!-- 사용자가 입력하는 FORM -->
		<form action="/Basic_Board_Project/UserController?action=login" method="post" id="loginForm">
			<div class="form-group">
				<label for="USERID">ID</label>
			    <input type="text" id="USERID" name="USERID" placeholder="아이디를 입력해주세요" autofocus autocomplete="off" required />
			</div>
			<div class="form-group">
			 	<label for="PASSWORD">PW</label>
			    <input type="PASSWORD" id="PASSWORD" name="PASSWORD" placeholder="비밀번호를 입력해주세요" autocomplete="off" required />
		 	</div>
		    
		    <button type="submit" class="btn btn-primary">로그인</button>
		 </form>
		
		<!-- 실제 서버로 전송되는 FORM -->
		<form action="/Basic_Board_Project/UserController?action=login" method="post" id="hiddenForm">
			<fieldset>
				<input type="hidden" name="USERID">
				<input type="hidden" name="PASSWORD">
			</fieldset>
		</form>
	</div>
</body>
</html>