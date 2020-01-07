<%@page import="com.VO.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html" %>


<%
	int USER_CODE = 0;
   	String NAME = null, USERID = null, PASSWORD = null;
   
   	UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
   	if (loginUser != null) {
		USER_CODE = loginUser.getUSER_CODE();
	   	USERID = loginUser.getUSERID();
		NAME = loginUser.getNAME();
		PASSWORD = loginUser.getPASSWORD();
   }
%>

<!DOCTYPE html>
<html>
<head>
<title>회원수정</title>

<!-- javaScript library load -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script type="text/javascript" src="resources/js/RSA/rsa.js"></script>
<script type="text/javascript" src="resources/js/RSA/jsbn.js"></script>
<script type="text/javascript" src="resources/js/RSA/prng4.js"></script>
<script type="text/javascript" src="resources/js/RSA/rng.js"></script>

<script type="text/javascript">

	$(function() {
		var NEW_NAME = null;
		var NEW_PASSWORD = null;
		var CHK_NEW_PASSWORD = null;
		var OLD_PASSWORD = null;
		
		var rsa = new RSAKey();
		rsa.setPublic("${modulus}", "${exponent}");

		$("#updateForm").submit(function(e) {
 			e.preventDefault();
 			
			NEW_NAME = $(this).find("#NEW_NAME").val();
			NEW_PASSWORD = $(this).find("#NEW_PASSWORD").val();
			CHK_NEW_PASSWORD = $(this).find("#CHK_NEW_PASSWORD").val();			
			
			if(NEW_PASSWORD == CHK_NEW_PASSWORD){				
				$("#FINAL_SUBMIT").css("display","");
			}else{
				alert("새 비밀번호가 일치하지 않습니다.");
				$(this).find("#NEW_PASSWORD").val("");
				$(this).find("#CHK_NEW_PASSWORD").val("");
			}			
		})
		
		$("#FINAL_SUBMIT").submit(function(e){
			alert("회원정보가 수정됩니다.");	
 			e.preventDefault();		
			
			OLD_PASSWORD = $(this).find("#OLD_PASSWORD").val();
			
			$("#hiddenForm input[name='NEW_NAME']").val(rsa.encrypt(NEW_NAME));
			$("#hiddenForm input[name='NEW_PASSWORD']").val(rsa.encrypt(NEW_PASSWORD));
			$("#hiddenForm input[name='OLD_PASSWORD']").val(rsa.encrypt(OLD_PASSWORD));
			
			// 임시 출력 alert!!//////////////////////////////////////
			alert("new name : " + NEW_NAME + "\nnew_password : " + NEW_PASSWORD + "\nold password : " + OLD_PASSWORD);
			alert("new_name : " + $("#hiddenForm input[name='NEW_NAME']").val() + "\n" 
					+ "new_password : " + $("#hiddenForm input[name='NEW_PASSWORD']").val() + "\n"
					+ "old_password : " + $("#hiddenForm input[name='OLD_PASSWORD']").val());
			//////////////////////////////////////////////////////
			
			$("#hiddenForm").submit();
		})
	})
</script>

</head>

<body>
	<div class="container mx-auto m-5 p-5 bg-light w-50 shadow">
	
		<H2>USER :: Update</H2>
		<HR>
		
		<form id=updateForm name=form1 method=post action="/Basic_Board_Project/UserController?action=update">
			<input type=hidden name="USER_CODE" value="<%= USER_CODE %>">
		
			<%= USERID %>님의 정보수정 화면입니다.
			<HR>
			
			<!-- 새로운 패스워드 입력창 -->
			<div class="input-group mb-3">
				<div class="input-group-prepend">
			    	<span class="input-group-text"><label for="PASSWORD">NEW PASSWORD</label></span>
			    </div>
			    <input class="form-control" type="password" name="NEW_PASSWORD" id="NEW_PASSWORD" value="" placeholder="새 비밀번호"/>
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
			    	<span class="input-group-text"><label for="PASSWORD">NEW PASSWORD</label></span>
			    </div>
			    <input class="form-control" type="password" name="CHK_NEW_PASSWORD" id="CHK_NEW_PASSWORD" value="" placeholder="새 비밀번호 확인"/>
			</div>
			
			<!-- 새로운 이름(별명) 입력창 -->
			<div class="input-group mb-3">
				<div class="input-group-prepend">
			    	<span class="input-group-text"><label for="NAME">NAME</label></span>
			    </div>
			  	<input class="form-control" type="text" name="NEW_NAME" id="NEW_NAME" value="<%=NAME %>" placeholder="새 별명"/>
			</div>
			
			<button type="submit" class="btn btn-warning">완료</button>
			<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>			
		</form>
		
		<!-- 기존 패스워드 입력창 -->
		<form action="" id=FINAL_SUBMIT style="display: none;">
			<div class="input-group mb-3" >
				<div class="input-group-prepend">
			    	<span class="input-group-text"><label for="PASSWORD">OLD PASSWORD</label></span>
			    </div>
			    <input class="form-control" type="password" name="OLD_PASSWORD" id="OLD_PASSWORD" value="" placeholder="기존 비밀번호"/>
			<button type="submit" class="btn btn-warning">완료</button>
			</div>
		</form>
		
		<!-- 실제 서버로 전송되는 form -->
		<form id=hiddenForm action="/Basic_Board_Project/UserController?action=update" method="post">
			<input type="hidden" name="NEW_NAME"/>
			<input type="hidden" name="NEW_PASSWORD"/>
			<input type="hidden" name="OLD_PASSWORD"/>
		</form>
		
	</div>

</body>
</html>