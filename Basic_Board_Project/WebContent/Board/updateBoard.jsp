<%@page import="com.VO.FilesVO"%>
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

	<script type="text/javascript">
	
		$(function() {
			$("#deleteFile").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			})
		})
		
		function deleteFile(obj) {
			obj.parent().remove();
			
			var str = "<p><input type='file' name='FILE'><a class='btn btn-warning' id='deleteFile'>삭제</a>"
			$("#fileDiv").append(str);
			
			$("#deleteFile").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			})
		}
	
	</script>

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
	
	<form action="/Basic_Board_Project/BoardController?action=update&BOARD_CODE=${board.BOARD_CODE}&FILE_CODE=${file.FILE_CODE}" method="post" enctype="multipart/form-data">

		<table>
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			
			<tbody>
				<tr>
					<td>&nbsp;</td>
					<th colspan="2">작성자</th>
					<td><%= NAME %></td>
					<td>&nbsp;</td>
				</tr>
				<tr height="1" bgcolor="#dddddd">
					<td colspan="4"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">제목</td>
					<td><input type="text" name="TITLE" size="100" maxlength="100" value="${board.TITLE}"></td>
					<td>&nbsp;</td>
				</tr>
				<tr height="1" bgcolor="#dddddd">
					<td colspan="4"></td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">내용</td>
					<td><textarea name="CONTEXT" cols="100" rows="20">${board.CONTEXT}</textarea></td>
					<td>&nbsp;</td>
				</tr>
				<tr height="1" bgcolor="#dddddd">
					<td colspan="4"></td>
				</tr>
				<tr height="1" bgcolor="#82B5DF">
					<td colspan="4"></td>
				</tr>
			</tbody>
		</table>

		<br><br>
		
		<div id="fileDiv">
		<%
			FilesVO file = (FilesVO) request.getAttribute("fileContents");
			if(file.getFILE_ORIGINAL_NAME() == null) {
		%>
			<p>
				<input type="file" name="FILE">
				<a class="btn btn-warning" id="deleteFile">삭제</a>
			</p>
		<%
			} else {
		%>
			<p>
				첨부파일<br><hr>
				${file.FILE_ORIGINAL_NAME }
				<a href="/Basic_Board_Project/BoardController?action=deleteFile&FILE_CODE=${file.FILE_CODE}&BOARD_CODE=${board.BOARD_CODE}" class="btn btn-warning" >삭제</a>
			</p>
		<%
			}
		%>
		</div>
		
		<br><hr>
		
		<div align="center">
			<input type="submit" value="수정" class="btn btn-info" > 
			<a href="/Basic_Board_Project/BoardController?action=boardContents&BOARD_CODE=${board.BOARD_CODE}" class="btn btn-info">취소</a>
		</div>
		
	</form>
	
	</div>
</body>
</html>