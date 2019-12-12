package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;
import com.service.user.UserService;
import com.service.user.UserServiceImpl;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private BoardService boardService = new BoardServiceImpl();
	private UserService userService = new UserServiceImpl();

	public BoardController() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
			break;
		case "insert":
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
