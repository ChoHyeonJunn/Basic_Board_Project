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

	<h1>BOARD :: 글쓰기</h1>
		<%
			if (NAME != null) {
		%>
			<label>작성자 : <%=NAME%></label>
		<%
			}
		%>
	<form action="/Basic_Board_Project/BoardController?action=insert&USER_CODE=<%=USER_CODE%>"	method="post" enctype="multipart/form-data">
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
					<td><input type="text" name="TITLE" size="100" maxlength="100"></td>
					<td>&nbsp;</td>
				</tr>
				<tr height="1" bgcolor="#dddddd">
					<td colspan="4"></td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">내용</td>
					<td><textarea name="CONTEXT" cols="100" rows="20"></textarea></td>
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
			<p>
				<input type="file" name="FILE">
				<a class="btn btn-warning" id="deleteFile">삭제</a>
			</p>
		</div>
		
		<br><hr>
		
		<div align="center">
			<input type="submit" value="등록" class="btn btn-info" > 
			<a href="javascript:history.go(-1)" class="btn btn-info">돌아가기</a>
		</div>
	</form>
	
</body>
</html>