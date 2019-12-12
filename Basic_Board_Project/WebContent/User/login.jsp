<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc_header.html" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
</head>
<body>

<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
	<H2>USER :: 로그인</H2>
	
	<form action="/Basic_Board_Project/UserController?action=login" method="post">
		<div class="form-group">
			<label for="USERID">ID </label>
				<input type="text" class="form-control" name="USERID">
		</div>

		<div class="form-group">
			<label for="PASSWORD">PW : </label>
				<input type="password" class="form-control" name="PASSWORD">
		</div>
		
		<input type="submit" value="로그인">
			
	</form>

</body>
</html>