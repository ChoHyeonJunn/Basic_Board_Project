<%@page import="com.controller.BoardController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>Select All Data</title>
</head>
<body>
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
	------------------------------------------------------------------------
	
	<h1>Users Data</h1>
	<table>
		<thead>
			<tr>
				<th>BOARD_CODE</th>
				<th>USER_CODE</th>
				<th>TITLE</th>
				<th>CONTEXT</th>
				<th>COUNT_VIEW</th>
				<th>CREATE_DATE</th>
				<th>UPDATE_DATE</th>
			</tr>
		</thead>

		<c:forEach items="${boardsList}" var="boards">
			<tr>
				<td>${boards.BOARD_CODE}</td>
				<td>${boards.USER_CODE}</td>
				<td>${boards.TITLE}</td>
				<td>${boards.CONTEXT}</td>
				<td>${boards.COUNT_VIEW}</td>
				<td>${boards.CREATE_DATE}</td>
				<td>${boards.UPDATE_DATE}</td>
			</tr>
		</c:forEach>
	</table>
	------------------------------------------------------------------------
	
	<h1>Users Data</h1>
	<table>
		<thead>
			<tr>
				<th>BOARD_CODE</th>
				<th>USER_CODE</th>
				<th>CONTEXT</th>
				<th>COUNT_GOOD</th>
				<th>COUNT_BAD</th>
				<th>CREATE_DATE</th>
			</tr>
		</thead>

		<c:forEach items="${commentsList}" var="comments">
			<tr>
				<td>${comments.BOARD_CODE}</td>
				<td>${comments.USER_CODE}</td>
				<td>${comments.CONTEXT}</td>
				<td>${comments.COUNT_GOOD}</td>
				<td>${comments.COUNT_BAD}</td>
				<td>${comments.CREATE_DATE}</td>
			</tr>
		</c:forEach>
	</table>
	------------------------------------------------------------------------

</body>
</html>