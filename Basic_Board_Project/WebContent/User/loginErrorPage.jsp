<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String msg = (String)request.getAttribute("msg"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 에러</title>
</head>
<body>
	<h1><%= msg %></h1>
</body>
</html>