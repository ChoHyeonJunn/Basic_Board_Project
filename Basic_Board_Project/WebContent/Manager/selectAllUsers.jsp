<%@page import="com.controller.UserController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>전체 회원 정보</title>
</head>
<body>
	<a href="/Basic_Board_Project/ManagerController?action=listAllDatas"
		class="btn btn-info">전체 데이터 정보</a>
	<a href="/Basic_Board_Project/ManagerController?action=listAllUsers"
		class="btn btn-info">전체 회원 정보</a>

	<h1>Users Data</h1>
	<table>
		<thead>
			<tr>
				<th>USER_CODE</th>
				<th>USERID</th>
				<th>PASSWORD</th>
				<th>NAME</th>
				<th>CREATE_DATE</th>
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