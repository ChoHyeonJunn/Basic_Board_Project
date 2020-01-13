<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ include file="inc_header.html" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>

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

         $("#hiddenForm input[name='USERID']").val(rsa.encrypt(USERID));
         $("#hiddenForm input[name='PASSWORD']").val(rsa.encrypt(PASSWORD));

         // 임시 출력 alert!!//////////////////////////////////////
         alert("userid : " + $("#hiddenForm input[name='USERID']").val() + "\n" 
               + "password : " + $("#hiddenForm input[name='PASSWORD']").val() + "\n");
         //////////////////////////////////////////////////////
         
         //$("#hiddenForm").submit();
         
         $.ajax({
					
					type: "POST",
					url: "/Basic_Board_Project/UserController?action=login",
					data: {
						USERID : $("#hiddenForm input[name='USERID']").val(),
						PASSWORD : $("#hiddenForm input[name='PASSWORD']").val()
						},
					datatype: "JSON",
					
					success: function(args) {
						var res = JSON.parse(args).result;
						
						if(res == 0 || res == -1) {
							$("#login_check").text("ID, PW를 확인해주세요.");
							$("#login_check").attr("style", "color:red");
						} else if(res == 1) {
							location.href="/Basic_Board_Project/BoardController?action=listBoard";
						}
					},
					
					error : function(request, status, error) {
						alert("통신 실패");
						alert("code : " + request.status + "\n" +
								"message : " + request.responseText + "\n" +
								"error : " + error);
					}
				})
      })
   })
</script>

</head>
<body>

	<div class="container mx-auto mt-5 p-5 shadow" style="width: 50%">
		<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
	   
		<H2>USER :: 로그인</H2>
	   
	   <!-- 사용자에게 입력받는 form -->
		<form id=insertForm action="/Basic_Board_Project/UserController?action=login" method="post">
			<div class="form-group">
				<label for="USERID">ID </label>
	            <input id="USERID" type="text" class="form-control" name="USERID">
	      </div>
	
	      <div class="form-group">
				<label for="PASSWORD">PW : </label>
	            <input id="PASSWORD" type="password" class="form-control" name="PASSWORD">
	      </div>
	      
	      <div class="check_font" id="login_check"></div>
	      
	      <input type="submit" value="로그인">
	   </form>
	   
	   <!-- 실제 서버로 전송되는 form -->
	   <form id=hiddenForm action="/Basic_Board_Project/UserController?action=login" method="post">
	      <input type="hidden" name="USERID"/>
	      <input type="hidden" name="PASSWORD"/>
	   </form>
   </div>

</body>
</html>