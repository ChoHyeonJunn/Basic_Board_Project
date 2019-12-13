<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO"%>
<%@ include file="inc_header.html"%>

<%
	String NAME = null;
	int USER_CODE = 0;
	UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
	if (loginUser != null){
		NAME = loginUser.getNAME();
		USER_CODE = loginUser.getUSER_CODE();	
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>
</head>
<body>

	<h1>BOARD :: 글쓰기</h1>
		<%
			if (NAME != null) {
		%>
			<label>작성자 : <%=NAME%></label>
		<%
			}
		%>
	<form action="/Basic_Board_Project/BoardController?action=insert&USER_CODE=<%=USER_CODE%>"	method="post">
		<table>
			<tr>
				<td>&nbsp;</td>
				<td align="center">제목</td>
				<td><input name="TITLE" size="50" maxlength="100"></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="1" bgcolor="#dddddd">
				<td colspan="4"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td align="center">내용</td>
				<td><textarea name="CONTEXT" cols="50" rows="13"></textarea></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="1" bgcolor="#dddddd">
				<td colspan="4"></td>
			</tr>
			<tr height="1" bgcolor="#82B5DF">
				<td colspan="4"></td>
			</tr>
			<tr align="center">
				<td>&nbsp;</td>
				<td colspan="2">
				<input type="submit" value="등록" class="btn btn-info"> 
				<a href="javascript:history.go(-1)" class="btn btn-info">돌아가기</a>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>