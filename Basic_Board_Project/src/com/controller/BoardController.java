package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.VO.FilesVO;
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
		case "insert":
			//insertData();
			break;
		case "edit":
			//viewEdit();
			break;
		case "update":
			//updateData();
			break;
		case "delete":
			//deleteData();
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void listAllData() {

		try {
			ArrayList<UsersVO> usersList = dao.selectUsers();
			ArrayList<BoardsVO> boardsList = dao.selectBoards();
			ArrayList<CommentsVO> commentsList = dao.selectComments();
			ArrayList<FilesVO> filesList = dao.selectFiles();

			request.setAttribute("usersList", usersList);
			request.setAttribute("boardsList", boardsList);
			request.setAttribute("commentsList", commentsList);
			request.setAttribute("filesList", filesList);
			view = "/Board/selectAll.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
