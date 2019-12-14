<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.VO.UsersVO, com.VO.BoardsVO"%>

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
<title>글 내용</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr
						style="background: url('img/table_mid.gif') repeat-x; text-align: center;">
						<td width="5"><img src="img/table_left.gif" width="5"
							height="30" /></td>
						<td>내 용</td>
						<td width="5"><img src="img/table_right.gif" width="5"
							height="30" /></td>
					</tr>
				</table>
				<table width="413">
				<c:set var = "user" value="${userContents}"/>
				<c:set var = "board" value="${boardContents}"/>
					<tr>
						<td width="0">&nbsp;</td>
						<td align="center" width="76">글번호</td>
						<td width="319">${board.BOARD_CODE}</td>
						<td width="0">&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr>
						<td width="0">&nbsp;</td>
						<td align="center" width="76">조회수</td>
						<td width="319">${board.COUNT_VIEW}</td>
						<td width="0">&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr>
						<td width="0">&nbsp;</td>
						<td align="center" width="76">이름</td>
						<td width="319">${user.NAME}</td>
						<td width="0">&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr>
						<td width="0">&nbsp;</td>
						<td align="center" width="76">작성일</td>
						<td width="319">${board.CREATE_DATE}</td>
						<td width="0">&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr>
						<td width="0">&nbsp;</td>
						<td align="center" width="76">제목</td>
						<td width="319">${board.TITLE}</td>
						<td width="0">&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr>
						<td width="0">&nbsp;</td>
						<td width="399" colspan="2" height="200">${board.CONTEXT}
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4" width="407"></td>
					</tr>
					<tr height="1" bgcolor="#82B5DF">
						<td colspan="4" width="407"></td>
					</tr>
					<tr align="center">
						<td width="0">&nbsp;</td>
						<td colspan="2" width="399">
							<a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=listBoard">목록</a> 
							<%
							BoardsVO ELboard =(BoardsVO) request.getAttribute("boardContents");
							if (USER_CODE == ELboard.getUSER_CODE()) {
							%>							
 							
 							<a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=updateReady&BOARD_CODE=${board.BOARD_CODE}">수정</a>
							<a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=delete&BOARD_CODE=${board.BOARD_CODE}">삭제</a>
							<% 	
							}
							%> 
						<td width="0">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>