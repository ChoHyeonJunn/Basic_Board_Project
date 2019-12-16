package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;
import com.service.board.Paging;
import com.service.comment.CommentService;
import com.service.comment.CommentServiceImpl;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private BoardService boardService = new BoardServiceImpl();
	private CommentService commentService = new CommentServiceImpl();

	public BoardController() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.request = request;
		this.response = response;

		this.request.setCharacterEncoding("utf-8");
		this.response.setContentType("text/html;charset=utf-8");

		out = response.getWriter();
		String action = request.getParameter("action");

		if (action == null) {
			action = "listBoard";
		}

		switch (action) {
		case "listBoard":
			selectList();
			break;
		case "insert":
			insertBoard();
			selectList();
			break;
		case "boardContents":
			boardContents();
			break;
		case "updateReady":
			updateReady();
			break;
		case "update":
			update();
			boardContents();
			break;
		case "delete":
			delete();
			selectList();
			break;
		case "search":
			if (!searchList()) {
				selectList();
			}
			break;
		case "insertComment":
			insertComment();
			boardContents();
			break;
		case "updateComment":
			updateComment();
			boardContents();
			break;
		case "deleteComment":
			deleteComment();
			boardContents();
			break;
		case "statusComment":
			statusComment();
			boardContents();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void statusComment() {
		String status = request.getParameter("status");

		if (status.equals("good"))
			commentService.goodComment(Integer.parseInt(request.getParameter("COMMENT_CODE")));
		if (status.equals("bad"))
			commentService.badComment(Integer.parseInt(request.getParameter("COMMENT_CODE")));

	}

	private void deleteComment() {
		commentService.deleteComment(Integer.parseInt(request.getParameter("COMMENT_CODE")));
		boardService.decreaseCountComment(Integer.parseInt(request.getParameter("BOARD_CODE")));
	}

	private void updateComment() {
		if (request.getParameter("CONTEXT") == "")
			return;
		CommentsVO comment = new CommentsVO();

		comment.setBOARD_CODE(Integer.parseInt(request.getParameter("BOARD_CODE")));
		comment.setCOMMENT_CODE(Integer.parseInt(request.getParameter("COMMENT_CODE")));
		comment.setCONTEXT(request.getParameter("CONTEXT"));

		commentService.updateComment(comment);
	}

	private void insertComment() {
		if (request.getParameter("CONTEXT") == "")
			return;
		CommentsVO comment = new CommentsVO();

		comment.setBOARD_CODE(Integer.parseInt(request.getParameter("BOARD_CODE")));
		comment.setUSER_CODE(Integer.parseInt(request.getParameter("USER_CODE")));
		comment.setCONTEXT(request.getParameter("CONTEXT"));

		commentService.insertComment(comment);
		boardService.increaseCountComment(Integer.parseInt(request.getParameter("BOARD_CODE")));
	}

	// 게시글 리스트
	private void selectList() throws IOException {
		Paging paging = new Paging();
		paging.makeLastPageNum();

		int curPage = 1; // 현재 페이지
		if (request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
			paging.makeBlock(curPage);
			request.setAttribute("curPageNum", curPage);
		} else {
			paging.makeBlock(curPage);
			request.setAttribute("curPageNum", curPage);
		}

		Integer blockStartNum = paging.getBlockStartNum();
		Integer blockLastNum = paging.getBlockLastNum();
		Integer lastPageNum = paging.getLastPageNum();

		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum);

		request.setAttribute("boardList", boardService.selectBoardsListData(curPage));

		view = "/Board/boardList.jsp";
	}

	private boolean searchList() {
		if (request.getParameter("condition") == "") {
			System.out.println("키워드가 없습니다!");
			return false;
		} else {
			int option = Integer.parseInt(request.getParameter("opt"));
			String condition = request.getParameter("condition");

			Paging paging = new Paging();
			paging.makeLastPageNum(option, condition);

			int curPage = 1; // 현재 페이지
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
				paging.makeBlock(curPage);
				request.setAttribute("curPageNum", curPage);
			} else {
				paging.makeBlock(curPage);
				request.setAttribute("curPageNum", curPage);
			}

			Integer blockStartNum = paging.getBlockStartNum();
			Integer blockLastNum = paging.getBlockLastNum();
			Integer lastPageNum = paging.getLastPageNum();

			request.setAttribute("blockStartNum", blockStartNum);
			request.setAttribute("blockLastNum", blockLastNum);
			request.setAttribute("lastPageNum", lastPageNum);

			request.setAttribute("boardList", boardService.selectSearchListData(curPage, option, condition));

			view = "/Board/boardList.jsp";

			System.out.println("search 성공!!");
			return true;
		}
	}

	// 게시글 등록
	private void insertBoard() {
		BoardsVO insertBoard = new BoardsVO();

		insertBoard.setUSER_CODE(Integer.parseInt(request.getParameter("USER_CODE")));
		insertBoard.setTITLE(request.getParameter("TITLE"));
		insertBoard.setCONTEXT(request.getParameter("CONTEXT"));

		boardService.insertBoard(insertBoard);
	}

	// 글 내용
	private void boardContents() {
		int BOARD_CODE = Integer.parseInt(request.getParameter("BOARD_CODE"));

		boardService.increaseCountView(BOARD_CODE);

		request.setAttribute("boardContents", boardService.selectBoardContents(BOARD_CODE).get("boardsVO"));
		request.setAttribute("userContents", boardService.selectBoardContents(BOARD_CODE).get("usersVO"));
		request.setAttribute("commentsList", commentService.selectComments(BOARD_CODE));

		view = "/Board/boardContents.jsp";

	}

	private void updateReady() {
		int BOARD_CODE = Integer.parseInt(request.getParameter("BOARD_CODE"));

		request.setAttribute("boardContents", boardService.selectBoardContents(BOARD_CODE).get("boardsVO"));
		view = "/Board/updateBoard.jsp";
	}

	private void update() {
		BoardsVO updateBoard = new BoardsVO();
		updateBoard.setTITLE(request.getParameter("TITLE"));
		updateBoard.setCONTEXT(request.getParameter("CONTEXT"));
		updateBoard.setBOARD_CODE(Integer.parseInt(request.getParameter("BOARD_CODE")));

		boardService.updateBoard(updateBoard);
	}

	private void delete() {
		boardService.deleteBoard(Integer.parseInt(request.getParameter("BOARD_CODE")));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
