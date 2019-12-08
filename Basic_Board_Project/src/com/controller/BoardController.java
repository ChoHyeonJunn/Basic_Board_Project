package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.VO.UsersVO;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private BoardDAO dao = new BoardDAOImpl();

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			listAllData();
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void listAllData() {

		try {
			ArrayList<UsersVO> usersList = dao.selectUsers();
			ArrayList<BoardsVO> boardsList = dao.selectBoards();
			ArrayList<CommentsVO> commentsList = dao.selectComments();

			request.setAttribute("usersList", usersList);
			request.setAttribute("boardsList", boardsList);
			request.setAttribute("commentsList", commentsList);
			view = "/Board/selectAll.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
