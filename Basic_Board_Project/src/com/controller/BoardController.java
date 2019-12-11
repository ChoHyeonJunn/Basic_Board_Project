package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.BoardDAO;
import com.DAO.BoardDAOImpl;
import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;
import com.service.BoardServiceImpl;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private BoardDAO dao = new BoardDAOImpl();
	private BoardServiceImpl boardService = new BoardServiceImpl();

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
			action = "listAll";
		}

		switch (action) {
		case "listAll":
			listAllUsers();
//			request.setAttribute("usersList", boardService.selectUsersListData());
//			request.setAttribute("boardsList", boardService.selectBoardsListData());
//			request.setAttribute("commentsList", boardService.selectCommentsListData());
//			request.setAttribute("filesList", boardService.selectFilesListData());
//			view = "/Board/selectAll.jsp";
			break;
		case "insert":
			insertUser();
			listAllUsers();
			break;
		case "edit":
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
	
	// 회원 리스트 전체 출력
	public void listAllUsers() {

		List<UsersVO> usersList = null;
		try {
			usersList =  boardService.selectUsersListData();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		request.setAttribute("usersList", usersList);
		view = "/Board/selectAllUsers.jsp";
	}
	
	// 회원가입
	private void insertUser() throws IOException {
		UsersVO user = new UsersVO();
		
		user.setUSERID(request.getParameter("USERID"));
		user.setPASSWORD(request.getParameter("PASSWORD"));
		user.setNAME(request.getParameter("NAME"));
		
		if(boardService.insertUser(user)) {
			System.out.println("USERS DB 입력 성공!");
		} else {
			throw new IOException("USERS DB 입력 오류");
		}
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
