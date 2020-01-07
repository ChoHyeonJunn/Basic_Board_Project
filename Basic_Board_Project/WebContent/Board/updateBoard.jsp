<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO"%>
<%@ include file="inc_header.html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>글 수정</title>
</head>
<body>

	<h1>BOARD :: 글 수정</h1>
		<%
			if (NAME != null) {
		%>
			<label>작성자 : <%=NAME%></label>
		<%
			}
		%>
	<c:set var="board" value="${boardContents}"/>
	<c:set var="file" value="${fileContents}" />
	
	<div class="container mx-auto m-5 p-5 bg-ligth shadow">	
	
	<form action="/Basic_Board_Project/BoardController?action=update&BOARD_CODE=${board.BOARD_CODE}" method="post">

		<table>
			<tr>
				<td>&nbsp;</td>
				<td align="center">제목</td>
				<td><input name="TITLE" size="120" maxlength="100" value="${board.TITLE}"></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="1" bgcolor="#dddddd">
				<td colspan="4"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td align="center">내용</td>
				<td><textarea name="CONTEXT" cols="120" rows="13">${board.CONTEXT}</textarea></td>
				<td>&nbsp;</td>
			</tr>
			<tr height="1" bgcolor="#dddddd">
				<td colspan="4"></td>
			</tr>
			<tr height="1" bgcolor="#82B5DF">
				<td colspan="4"></td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td align="center">첨부 파일</td>
				<td>
					${file.FILE_STORED_NAME }
					<a href="/Basic_Board_Project/BoardController?action=deleteFile&FILE_CODE=${file.FILE_CODE}&BOARD_CODE=${board.BOARD_CODE}" class="btn btn-warning" id="deleteFile">삭제</a>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr height="1" bgcolor="#dddddd">
				<td colspan="4"></td>
			</tr>
			
			<tr align="center">
				<td>&nbsp;</td>
				<td colspan="2">
					<input type="submit" value="수정" class="btn btn-info">
					<a href="javascript:history.go(-1)" class="btn btn-info">돌아가기</a>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
	
	</div>
</body>
</html>