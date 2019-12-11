<%@page import="com.controller.BoardController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 정보</title>
</head>
<body>
<h1>Users Data</h1>
	
	<a href="/Basic_Board_Project/Board/insertUser.jsp" class="btn btn-info">회원가입</a>
	
	<table>
		<thead>
			<tr>
				<th> USER_CODE</th>
				<th> USERID</th>
				<th> PASSWORD</th>
				<th> NAME</th>
				<th> CREATE_DATE</th>
			</tr>
		</thead>

		<c:forEach items="${usersList}" var="users">
			<tr>
				<td>${users.USER_CODE}</td>
				<td>${users.USERID}</td>
				<td>${users.PASSWORD}</td>
				<td>${users.NAME}</td>
				<td>${users.CREATE_DATE}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>