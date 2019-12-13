package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Manager.ManagerService;
import com.service.Manager.ManagerServiceImpl;
import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;
import com.service.comment.CommentService;
import com.service.comment.CommentServiceImpl;
import com.service.file.FileService;
import com.service.file.FileServiceImpl;
import com.service.user.UserService;
import com.service.user.UserServiceImpl;

@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private ManagerService managerService = new ManagerServiceImpl();

	private BoardService boardService = new BoardServiceImpl();
	private UserService userService = new UserServiceImpl();
	private FileService fileService = new FileServiceImpl();
	private CommentService commentService = new CommentServiceImpl();

	public ManagerController() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.request = request;
		this.response = response;
		System.out.println("userController");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		out = response.getWriter();
		String action = request.getParameter("action");

		if (action == null) {
			action = "listAllDatas";
		}

		switch (action) {
		case "listAllDatas":
			listAllDatas();
			break;
		case "listAllUsers":
			listAllUsers();
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void listAllDatas() {
		@SuppressWarnings("rawtypes")
		Map<String, ArrayList> selectAllDataMap = managerService.selectAllData();

		request.setAttribute("usersList", selectAllDataMap.get("usersList"));
		request.setAttribute("boardsList", selectAllDataMap.get("boardsList"));
		request.setAttribute("commentsList", selectAllDataMap.get("commentsList"));
		request.setAttribute("filesList", selectAllDataMap.get("filesList"));
		view = "/Manager/selectAllDatas.jsp";

	}

	// 회원 리스트 전체 출력
	public void listAllUsers() {
		request.setAttribute("usersList", managerService.selectUsersListData());
		view = "/Manager/selectAllUsers.jsp";
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
