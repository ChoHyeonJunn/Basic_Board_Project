<%@page import="com.VO.FilesVO"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" import="com.VO.UsersVO, com.VO.BoardsVO"%>

<%@ include file="inc_header.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   String NAME = null;
   int USER_CODE = 0;
   UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
   if (loginUser != null) {
      NAME = loginUser.getNAME();
      USER_CODE = loginUser.getUSER_CODE();
   }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 내용</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
   function change(COMMENT_CODE, BOARD_CODE, content) {
      var delUpdateButton = document.getElementById("UpdateButton"+COMMENT_CODE);
      var delContent = document.getElementById("Content"+COMMENT_CODE);
      
      delUpdateButton.remove();
      delContent.remove();
      
      var updateForm = document.getElementById("updateForm"+COMMENT_CODE);
      
      var str = '<form action="/Basic_Board_Project/BoardController?action=updateComment" method="post">'+
         '   <input type="hidden" name="BOARD_CODE" value="'+ BOARD_CODE +'">'+
         '   <input type="hidden" name="COMMENT_CODE" value="'+ COMMENT_CODE +'">'+
         '   <textarea cols="120" rows="3" name="CONTEXT">'+ content +'</textarea>'+      
         '   <input type="submit" value="수정" class="btn btn-dark">'+
         '</form>';
   
      
      var addForm = document.createElement("div");
      addForm.innerHTML = str;
      updateForm.appendChild(addForm);
   }
   
   function createSubComment(COMMENT_CODE){
      var subCommentForm = document.getElementById("subCommemtForm"+COMMENT_CODE);
      subCommentForm.setAttribute("style","display; padding-left: 50px;");
   }
   
   function cancelSubComment(COMMENT_CODE){
      var subCommentForm = document.getElementById("subCommemtForm"+COMMENT_CODE);
      subCommentForm.setAttribute("style","display: none;");
   }

   $(function(){
	   //$("#commentForm").children("div").css("margin-left","50px");
	   $("#commentForm").children("div").each(function() {
		   
		   if($(this).prop("id") > 0) {
			   $(this).css("margin-left","50px");
			}
		   
		   var name = $(this).find("span[class=name]").eq(0).text();
	       $(this).find("textarea").text("@" + name + " ");
	   });
	})
   
</script>

</head>
<body>
   <c:set var="user" value="${userContents}" />
   <c:set var="board" value="${boardContents}" />
   <c:set var="file" value="${fileContents}" />
   <div class="container mx-auto m-5 p-5 bg-ligth shadow">
      
      <table style="width: 100%; cellpadding: 0; cellspacing: 0; border: 0;">
         <tr>
            <td>
               <table style="width: 100%">
                  <tr>
                     <td align="center" width="76">글번호</td>
                     <td width="319">${board.BOARD_CODE}</td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr>
                     <td align="center" width="76">조회수</td>
                     <td width="319">${board.COUNT_VIEW}</td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr>
                     <td align="center" width="76">이름</td>
                     <td width="319">${user.NAME}</td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr>
                     <td align="center" width="76">작성일</td>
                     <td width="319">${board.CREATE_DATE}</td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr>
                     <td align="center" width="76">제목</td>
                     <td width="319">${board.TITLE}</td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr>
                     <td width="399" colspan="2" height="200">${board.CONTEXT}
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                  </tr>
                  <tr height="1" bgcolor="#82B5DF">
                     <td colspan="4" width="407"></td>
                  </tr>

                  <tr>
                     <td width="0">&nbsp;</td>
                     <td width="399" height="50">
                     <%
                        FilesVO file = (FilesVO) request.getAttribute("fileContents");
                        if(file == null) {
                     %>
                           첨부파일 없음
                     <%
                        } else {
                     %>
                           첨부파일
                           <a href="/Basic_Board_Project/FileController?FILE_CODE=${file.FILE_CODE }">${file.FILE_ORIGINAL_NAME }</a>(${file.FILE_SIZE })
                     <%
                        }
                     %>
                     </td>
                  </tr>
                  <tr height="1" bgcolor="#dddddd">
                     <td colspan="4" width="407"></td>
                     
                  <tr align="center">
                     <td colspan="2" width="399">
                     <a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=listBoard">목록</a>
                        <%
                           BoardsVO ELboard = (BoardsVO) request.getAttribute("boardContents");
                           if (USER_CODE == ELboard.getUSER_CODE()) {
                        %>
                        <a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=updateReady&BOARD_CODE=${board.BOARD_CODE}">수정</a>
                        <a class="btn btn-dark" href="/Basic_Board_Project/BoardController?action=delete&BOARD_CODE=${board.BOARD_CODE}">삭제</a>
                        <%
                           }
                        %>
                  </tr>
               </table>
            </td>
         </tr>
      </table>


      <!-- Reply Form {s} -->
      <div class="container mx-auto m-4 p-4 bg-ligth shadow">
         <%
            if (NAME != null) {
         %>
         <!-- 댓글 달기 -->
         <form action="/Basic_Board_Project/BoardController?action=insertComment" method="post">
            <!-- 댓글 수정, 삭제를 위한 파라미터 -->
            <input type="hidden" name="BOARD_CODE" value="${board.BOARD_CODE }">
            <input type="hidden" name="USER_CODE" value="<%=USER_CODE%>">
            <textarea cols="120" rows="3" name="CONTEXT" placeholder="여러분의 소중한 댓글을 입력해주세요."></textarea>
            &nbsp; 
            <input type="submit" value="댓글달기" class="btn btn-dark">
         </form>
         <%
            }
         %>

      <!-- 댓글 리스트 -->
         
       <div id="commentForm">
         <c:forEach var="co" items="${commentsList}">

            <!--  class="container mx-auto m-2 p-2 bg-ligth shadow" -->
            <div id="${co.GROUP_DEPTH}" style="margin: 10px;">   
               <span class="name">${co.NAME }</span> 
               <span class="date">${co.CREATE_DATE}</span>

               <c:set var="content" value="${co.CONTEXT }" />
               <c:set var="user_code" value="<%=USER_CODE %>"/>
               
               <c:if test="${co.USER_CODE eq user_code}">
                  <a href="javascript:change(${co.COMMENT_CODE},${co.BOARD_CODE},'${content }')" id="UpdateButton${co.COMMENT_CODE}">수정</a> &nbsp;
                  <a href="/Basic_Board_Project/BoardController?action=deleteComment&BOARD_CODE=${board.BOARD_CODE}&COMMENT_CODE=${co.COMMENT_CODE}&GROUP_DEPTH=${co.GROUP_DEPTH}&GROUP_NO=${co.GROUP_NO}">삭제</a>
               </c:if>
               
               <div id = "Content${co.COMMENT_CODE}">${content}</div>
               <div id = "updateForm${co.COMMENT_CODE}"></div>
               
               <div>
                  <c:if test="${co.USER_CODE eq user_code || user_code eq 0}">
                        좋아요 ${co.COUNT_GOOD } &nbsp; 싫어요 ${co.COUNT_BAD } &nbsp;
                  </c:if>
                  <input type="button" onclick="createSubComment(${co.COMMENT_CODE}); subName();" value="답글">
               </div>
               
               <!-- 대댓글 달기 -->
               <div id = "subCommemtForm${co.COMMENT_CODE}" style="padding-left: 50px; display: none;">
                  <form action="/Basic_Board_Project/BoardController?action=insertSubComment" method="post">
                     <input type="hidden" name="BOARD_CODE" value="${board.BOARD_CODE }">
                      <input type="hidden" name="USER_CODE" value="<%=USER_CODE%>">
                     <input type="hidden" name="GROUP_NO" value="${co.GROUP_NO }">
                     <input type="hidden" name="GROUP_ORDER" value="${co.GROUP_ORDER }">
                     <input type="hidden" name="GROUP_DEPTH" value="${co.GROUP_DEPTH }">
         
                      <textarea cols="120" rows="3" name="CONTEXT" placeholder="여러분의 소중한 댓글을 입력해주세요." ></textarea>
                      &nbsp;
                     <div style="margin-left:700px;">
                        <input type="button" value="취소" onclick="cancelSubComment(${co.COMMENT_CODE});" class="btn btn-dark" style="margin-right: 10px;">
                         <input type="submit" value="댓글달기" class="btn btn-dark">
                     </div>
                   </form>              
               </div>
               
               <c:if test="${user_code ne 0 && co.USER_CODE ne user_code}">
                  <div>                     
                     <a href="/Basic_Board_Project/BoardController?action=statusComment&BOARD_CODE=${board.BOARD_CODE}&COMMENT_CODE=${co.COMMENT_CODE}&status=good">좋아요 ${co.COUNT_GOOD }</a>
                     &nbsp;
                     <a href="/Basic_Board_Project/BoardController?action=statusComment&BOARD_CODE=${board.BOARD_CODE}&COMMENT_CODE=${co.COMMENT_CODE}&status=bad">싫어요 ${co.COUNT_BAD }</a>
                  </div>
               </c:if>
            </div>
         </c:forEach>         
         </div>
         
      </div>
      
   </div>
   
   

</body>
</html>