<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html"%>

<%
	String NAME = null;
	UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
	if (loginUser != null)
		NAME = loginUser.getNAME();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 목록</title>

<script type="text/javascript">
	$(function name() {
		$("#logout")
				.click(
						function() {
							location.href = "/Basic_Board_Project/UserController?action=logout";
						});
	});
</script>


</head>
<body>
	
		<%
			if (NAME != null) {
		%>
		<div id="userInfo" style="height: 10%; with: 100%; padding: 20px; text_align:center;">
			
			<button id="changeInfo" class="btn btn-info">정보수정</button>
			<button id="logout" class="btn btn-info">로그아웃</button>
			<a href="/Basic_Board_Project/User/updateUser.jsp" class="btn btn-info">회원정보</a>
			<a href="/Basic_Board_Project/Board/userBoardList.jsp" class="btn btn-info">내가쓴글</a> 
			<br> 
			<label><%=NAME%>님 환영합니다.</label>
			<br> <br>

		</div>
		<%
			}
		%>

	<div style="height: 80%; with: 100%; padding: 20px">
		<%
			if (NAME == null) {
		%>
		<a href="/Basic_Board_Project/User/insertUser.jsp" class="btn btn-info">회원가입</a> 
		<a href="/Basic_Board_Project/User/login.jsp" class="btn btn-info">로그인</a>
		<%
			}
		%>
		<div style="text-align:center; ">
			<h1>BOARD :: 목록</h1>
	
			<table>
				<thead>
					<tr>
						<th>  BOARD_CODE</th>
						<th>  TITLE</th>
						<th>  COUNT_COMMENT</th>
						<th>  USER_NAME</th>
						<th>  CREATE_DATE</th>
						<th>  COUNT_VIEW</th>
					</tr>
				</thead>
	
				<c:forEach items="${boardList}" var="boards">
					<tr>
						<td>${boards.BOARD_CODE}</td>
						<td>${boards.TITLE}</td>
						<td>${boards.COUNT_COMMENT}</td>
						<td>${boards.NAME}</td>
						<td>${boards.CREATE_DATE}</td>
						<td>${boards.COUNT_VIEW}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<%
			if (NAME != null) {
		%>
		<a href="/Basic_Board_Project/Board/insertBoard.jsp" class="btn btn-info">글쓰기</a>
		<%
			}
		%>
	</div>
</body>
</html>