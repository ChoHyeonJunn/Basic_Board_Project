<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc_header.html"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>
</head>
<body>

	<h1>BOARD :: 글쓰기</h1>

	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td>&nbsp;</td>
						<td align="center">제목</td>
						<td><input name="title" size="50" maxlength="100"></td>
						<td>&nbsp;</td>
					</tr>
					<tr height="1" bgcolor="#dddddd">
						<td colspan="4"></td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td align="center">내용</td>
						<td><textarea name="memo" cols="50" rows="13"></textarea></td>
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
						<td colspan="2"><input type=button value="등록"> <input
							type=button value="취소">
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
</body>
</html>