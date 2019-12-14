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
</head>

<body>
<div class="container mx-auto m-5 p-5 bg-light w-50 shadow">
	<H2>USER :: Update</H2>
	<HR>
	<form name=form1 method=post action="/Basic_Board_Project/UserController">
	<input type=hidden name="USER_CODE" value="${user.USER_CODE}">
	<input type=hidden name="action" value="update">
	
	<%= USERID %>님의 정보수정 화면입니다.
	<HR>
	
	<div class="input-group mb-3">
		<div class="input-group-prepend">
	      <span class="input-group-text"><label for="PASSWORD">PASSWORD</label></span>
	    </div>
	    <input class="form-control" type="password" name="PASSWORD" value="${user.PASSWORD}"/>
	</div>
	
	<div class="input-group mb-3">
		<div class="input-group-prepend">
	      <span class="input-group-text"><label for="NAME">NAME</label></span>
	    </div>
	  	<input class="form-control" type="text" name="NAME" value="${user.NAME}"/>
	</div>
	
		<button type="submit" class="btn btn-warning">완료</button>
		<a class="btn btn-dark" href="javascript:history.go(-1)">BACK</a><p>
		
	</form>
</div>

</body>
</html>