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
import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;

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

		request.setAttribute("boardList", boardService.selectBoardsListData());

		view = "/Board/boardList.jsp";
	}

	// 게시글 등록
	private void insertBoard() {
		BoardsVO insertBoard = new BoardsVO();
//		System.out.println(request.getParameter("NAME"));
//		System.out.println(request.getParameter("USER_CODE"));
//		System.out.println(request.getParameter("TITLE"));
//		System.out.println(request.getParameter("CONTEXT"));

		insertBoard.setUSER_CODE(Integer.parseInt(request.getParameter("USER_CODE")));
		insertBoard.setTITLE(request.getParameter("TITLE"));
		insertBoard.setCONTEXT(request.getParameter("CONTEXT"));

		boardService.insertBoard(insertBoard);
	}
	
	// 글 내용
	private void boardContents() {

		int BOARD_CODE = Integer.parseInt(request.getParameter("BOARD_CODE"));
		
		boardService.increaseCountView(BOARD_CODE);
		request.setAttribute("boardContents", boardService.selectBoardContents(BOARD_CODE));
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
