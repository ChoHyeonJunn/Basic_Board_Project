package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VO.BoardsVO;
import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;
import com.service.board.Paging;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private BoardService boardService = new BoardServiceImpl();

	public BoardController() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.request = request;
		this.response = response;

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

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
		case "update":
			break;
		case "delete":
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// 게시글 리스트
	private void selectList() throws IOException {
		Paging paging = new Paging();

		int curPage = 1;

		paging.makeLastPageNum();
		paging.makeBlock(curPage);

		if (request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
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
		view = "/Board/boardContents.jsp";

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
