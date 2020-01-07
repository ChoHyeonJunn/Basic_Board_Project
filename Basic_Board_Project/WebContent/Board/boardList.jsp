<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="inc_header.html"%>

<%
	String NAME = null;
	UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
	if (loginUser != null)
		NAME = loginUser.getNAME();
	

	String option = request.getParameter("option");
	String condition = request.getParameter("condition");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

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


	<div class="container mx-auto m-5 p-5 bg-ligth shadow">
	
		<h1><a href="/Basic_Board_Project/BoardController?action=listBoard">BOARD :: 목록</a></h1>
		<%
			if (NAME != null) {
		%>
		<div id="userInfo"
			style="height: 10%; with: 100%; padding: 20px; text_align: center;">

			<button id="logout" class="btn btn-info">로그아웃</button>
			<a href="/Basic_Board_Project/User/userContents.jsp" class="btn btn-info">회원정보</a> 
			<a href="/Basic_Board_Project/BoardController?action=search&option=3&condition=<%=NAME%>" class="btn btn-info">내가쓴글</a> 
			<br> 
			<label><%=NAME%>님 환영합니다.</label> 
			<br> <br>

		</div>
		<%
		}
		%>
		
		
		<%
			if (NAME == null) {
		%>
		<a href="/Basic_Board_Project/UserController?action=insertUserPage" class="btn btn-info">회원가입</a> 
		<a href="/Basic_Board_Project/UserController?action=loginPage" class="btn btn-info">로그인</a>
		<%
			}
		%>
		<div class="container mx-auto m-5 p-5 bg-ligth shadow">
			<div id="searchForm">				
				<form action="/Basic_Board_Project/BoardController?action=search" method="post">
					<select name="option">
						<option value="0" <c:if test="${option == '0'}">selected</c:if>>제목</option>
						<option value="1" <c:if test="${option == '1'}">selected</c:if>>내용</option>
						<option value="2" <c:if test="${option == '2'}">selected</c:if>>제목+내용</option>
						<option value="3" <c:if test="${option == '3'}">selected</c:if>>글쓴이</option>
					</select>
					<%
						
						if (condition == null) {
					%>
					<input type="text" size="20" name="condition"/>
					<%
						} else {
							request.setAttribute("option", option);
							request.setAttribute("condition", condition);
					%>
					<input type="text" size="20" name="condition" value="${condition}"/>
					<%
						}
					%>
					&nbsp; 
					<input type="submit" value="검색" />
				</form>
			</div>



			<table>
				<thead>
					<tr>
						<th>BOARD_CODE</th>
						<th>TITLE</th>
						<th>COUNT_COMMENT</th>
						<th>USER_NAME</th>
						<th>CREATE_DATE</th>
						<th>COUNT_VIEW</th>
					</tr>
				</thead>
				<c:forEach items="${boardList}" var="boards">
					<tr>
						<td>${boards.BOARD_CODE}</td>
						<td>						
						<a href="/Basic_Board_Project/BoardController?action=boardContents&BOARD_CODE=${boards.BOARD_CODE}">${boards.TITLE}</a>
						</td>
						<td>${boards.COUNT_COMMENT}</td>
						<td>${boards.NAME}</td>
						<td>${boards.CREATE_DATE}</td>
						<td>${boards.COUNT_VIEW}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="container-fluid w-50 mx-auto">
			<ul class="pagination">


				<c:set var="option" value="${option}" />
				<c:set var="condition" value="${condition}" />
				
				
				<c:if test="${ curPageNum > 5 }">
					<c:if test="${condition != null }">		
						<p>${condition}</p>				
						<a href="/Basic_Board_Project/BoardController?curPage=${ blockStartNum - 1 }&option=${option}&condition=${condition}">&nbsp;◀</a>
					</c:if>
					<c:if test="${condition == null }">						
						<a href="/Basic_Board_Project/BoardController?curPage=${ blockStartNum - 1 }">&nbsp;◀</a>
					</c:if>
				</c:if>

				<c:forEach var="i" begin="${ blockStartNum }" end="${ blockLastNum }">
					<c:choose>

						<c:when test="${ i > lastPageNum }">
							<li class="page-item disabled">&nbsp;${i}</li>
						</c:when>

						<c:when test="${ i == curPageNum }">
							<li class="page-item disabled">&nbsp;${i}</li>
						</c:when>

						<c:otherwise>
							<c:if test="${condition != null }">	
								<li class="page-item disabled"><a
									href="/Basic_Board_Project/BoardController?curPage=${ i }&option=${option}&condition=${condition}">&nbsp;${i}</a></li>
							</c:if>
							<c:if test="${condition == null }">
								<li class="page-item disabled"><a
									href="/Basic_Board_Project/BoardController?curPage=${ i }">&nbsp;${i}</a></li>
							</c:if>
						</c:otherwise>

					</c:choose>
				</c:forEach>

				<c:if test="${ lastPageNum > blockLastNum }">
					<a href="/Basic_Board_Project/BoardController?curPage=${ blockLastNum + 1 }&option=${option}&condition=${condition}">&nbsp;▶</a>
				</c:if>
			</ul>
		</div>

		<%
			if (NAME != null) {
		%>
		<a href="/Basic_Board_Project/Board/insertBoard.jsp"
			class="btn btn-info">글쓰기</a>
		<%
			}
		%>
	</div>
</body>
</html>