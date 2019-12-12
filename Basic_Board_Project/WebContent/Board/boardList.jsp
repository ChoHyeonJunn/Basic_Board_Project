<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html" %>

<% UsersVO loginUser = (UsersVO)session.getAttribute("loginUser"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 목록</title>
</head>
<body>
	<div id="userInfo">
        <label>님 환영합니다.</label><br>
        <button id="changeInfo">정보수정</button>
        <button id="logout">로그아웃</button>
    </div>

	<script type="text/javascript">
        $(function name() {
            $("#logout").click(function() {
                location.href="/Basic_Board_Project/UserController?action=logout";
			});
		});
	</script>

	<a href="/Basic_Board_Project/User/insertUser.jsp" class="btn btn-info">회원가입</a>
	<a href="/Basic_Board_Project/User/login.jsp" class="btn btn-info">로그인</a>
	<a href="/Basic_Board_Project/User/updateUser.jsp" class="btn btn-info">회원정보</a>
	<a href="/Basic_Board_Project/Board/userBoardList.jsp" class="btn btn-info">내가쓴글</a>
	
	<h1>BOARD :: 목록</h1>
	
	<table>
		<thead>
			<tr>
				<th>BOARD_CODE</th>
				<th>TITLE</th>
				<th>COUNT_COMMENT</th>
				<th>USER_CODE</th>
				<th>CREATE_DATE</th>
				<th>COUNT_VIEW</th>
			</tr>
		</thead>

		<c:forEach items="${boardList}" var="boards">
			<tr>
				<td>${boards.BOARD_CODE}</td>
				<td>${boards.TITLE}</td>
				<td>${boards.COUNT_COMMENT}</td>
				<td>${boards.USER_CODE}</td>
				<td>${boards.CREATE_DATE}</td>
				<td>${boards.COUNT_VIEW}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/Basic_Board_Project/Board/insertBoard.jsp" class="btn btn-info">글쓰기</a>	
	

</body>
</html>