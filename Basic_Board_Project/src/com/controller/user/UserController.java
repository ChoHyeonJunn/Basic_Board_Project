package com.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.UsersVO;
import com.service.user.UserService;
import com.service.user.UserServiceImpl;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private UserDAO dao = new UserDAOImpl();
	private UserService userService = new UserServiceImpl();

	public UserController() {
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
			action = "listAll";
		}

		switch (action) {
		case "listAll":
			listAllUsers();
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
		request.setAttribute("usersList", userService.selectUsersListData());
		view = "/User/selectAllUsers.jsp";
	}

	// 회원가입
	private void insertUser() throws IOException {
		UsersVO user = new UsersVO();

		user.setUSERID(request.getParameter("USERID"));
		user.setPASSWORD(request.getParameter("PASSWORD"));
		user.setNAME(request.getParameter("NAME"));

		if (userService.insertUser(user)) {
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
