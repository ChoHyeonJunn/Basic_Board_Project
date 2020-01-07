<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.VO.UsersVO" %>
<%@ page import="com.DAO.user.UserDAO" %>
<%@ page import="com.service.user.UserService" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html" %>

<%
	int USER_CODE = 0;
	String NAME = null, USERID = null, PASSWORD = null;
	Date CREATE_DATE = null;
   
	UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
	
	if (loginUser != null) {
		USER_CODE = loginUser.getUSER_CODE();
		USERID = loginUser.getUSERID();
		NAME = loginUser.getNAME();
		PASSWORD = loginUser.getPASSWORD();
		CREATE_DATE = loginUser.getCREATE_DATE();
   }
%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	// 수정 확인
/* 	function check(USER_CODE) {
		PASSWORD = prompt('수정하려면 비밀번호를 입력하세요.');
		document.location.href = "/Basic_Board_Project/UserController?action=edit&USER_CODE=" + USER_CODE
				+ "&PASSWORD=" + PASSWORD;
	} */
	
	// 탈퇴 확인
	function delcheck(USER_CODE) {
		result = confirm("정말 탈퇴하시겠습니까 ?");

		if (result == true) {
			document.location.href = "/Basic_Board_Project/UserController?action=delete&USER_CODE=" + USER_CODE;
			document.form1.submit();
		} else
			return;
	}
</script>

<title>회원정보</title>
</head>

<body>
<div class="container mx-auto m-5 p-5 bg-light w-50 shadow">
	<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
	<H2>USER :: 정보 / 탈퇴</H2>

	<div class="container mx-auto m-5 p-5 bg-ligth shadow">
			<%= NAME %>님의 정보입니다.

			<table>
				<thead>
					<tr>
						<th>USER_CODE</th>
						<th>USERID</th>
						<th>NAME</th>
						<th>PASSWORD</th>
						<th>CREATE_DATE</th>
					</tr>
				</thead>
					<tr>
						<td><%= USER_CODE%></td>
						<td><%= USERID %></td>
						<td><%= NAME %></td>
						<td><%= PASSWORD %></td>
						<td><%= CREATE_DATE %></td>
					</tr>
			</table>
		</div>
		
		<a href="/Basic_Board_Project/UserController?action=edit" class="btn btn-warning">수정</a>
		<a href="javascript:delcheck(<%= USER_CODE%>)" class="btn btn-danger">탈퇴</a>
		
</div>

</body>
</html>