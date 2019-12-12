<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="inc_header.html" %>
	
	<script type="text/javascript">
		function delcheck() {
			result = confirm("정말 탈퇴하시겠습니까 ?");
		
			if(result == true){
				document.form1.action.value="delete";
				document.form1.submit();
			}
			else
				return;
		}
	</script>

<title>회원정보</title>
</head>

<body>
<div class="container mx-auto m-5 p-5 bg-light w-50 shadow">
	<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
	<H2>USER :: Update / Delete</H2>
	<HR>
	<form name=form1 method=post action="/Basic_Board_Project/UserController">
	<input type=hidden name="USER_CODE" value="${users.USER_CODE}">
	<input type=hidden name="action" value="update">

	<div class="input-group mb-3">
		<div class="input-group-prepend">
	      <span class="input-group-text"><label for="PASSWORD">PASSWORD</label></span>
	    </div>
	    <input class="form-control" type="password" name="PASSWORD" value="${users.PASSWORD}">
	</div>
	
	<div class="input-group mb-3">
		<div class="input-group-prepend">
	      <span class="input-group-text"><label for="NAME">NAME</label></span>
	    </div>
	  	<input class="form-control" type="text" name="NAME" value="${users.NAME}">
	</div>
	
		<button type="submit" class="btn btn-warning">수정</button>
		<button class="btn btn-danger" onclick="delcheck()">탈퇴</button>
		
	</form>
</div>

</body>
</html>