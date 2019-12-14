package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	private UserService userService = new UserServiceImpl();
	
	HttpSession session;
	String msg;
	
	
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
			action = "login";
		}

		switch (action) {
		
		// 회원가입
		case "insert":
			insertUser();
			break;
		
		// 로그인
		case "login":
			login();
			break;
		
		// 로그아웃
		case "logout":
			logout();
			break;
		
		// 회원정보 수정 화면
		case "edit":
			viewEdit();
			break;
		
		// 회원정보 수정
		case "update":
			update();
			break;
			
		case "delete":
			delete();
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// 회원가입
	private void insertUser() throws IOException {
		UsersVO user = new UsersVO();

		user.setUSERID(request.getParameter("USERID"));
		user.setPASSWORD(request.getParameter("PASSWORD"));
		user.setNAME(request.getParameter("NAME"));

		if (userService.insertUser(user)) {
			System.out.println("USERS DB 입력 성공!");
			view = "/User/login.jsp";
		} else {
			throw new IOException("USERS DB 입력 오류");
		}
	}


	// 로그인
	private void login() {
		
		UsersVO requestUser = new UsersVO();
		
		requestUser.setUSERID(request.getParameter("USERID"));
		requestUser.setPASSWORD(request.getParameter("PASSWORD"));
		
		UsersVO loginUser = userService.loginCheck(requestUser);
		
		if(loginUser.getStatus() == 1) {	// 로그인 성공
			
			view = "/BoardController?action=listBoard";	//다시 BoardController로 액션 보내기!
			session = request.getSession();
			session.setAttribute("loginUser", loginUser);
	
		} else {	// 로그인 실패

			if(loginUser.getStatus() == 0) {	// 패스워드 오류
				msg = "패스워드를 잘못 입력하셨습니다.";
			} else {
				msg = "존재하지 않는 아이디입니다.";
			}
			
			request.setAttribute("msg", msg);
			
			view = "/User/loginErrorPage.jsp";
		}
	}
	
	// 로그아웃
	private void logout() {
		// 세션 해제!
		request.getSession().invalidate();
		view = "/Board/boardList.jsp";
	}
	
	// 회원정보 수정 화면
	private void viewEdit() {
		//UsersVO userVO = userService.selectOneUser(Integer.parseInt(request.getParameter("USER_CODE")));
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");		
		
		if(!request.getParameter("PASSWORD").equals(loginUser.getPASSWORD())) {
			msg = "비밀번호가 틀렸습니다.";
			request.setAttribute("msg", msg);
			
			view = "/User/loginErrorPage.jsp";
		}
		else {
			request.setAttribute("user", loginUser);
			view = "/User/updateUser.jsp";	
		}
	}
	
	// 회원정보 수정
	private void update() {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");	
		
		String PASSWORD = loginUser.getPASSWORD();
		String NAME = loginUser.getNAME();
		
		loginUser.setPASSWORD(request.getParameter("PASSWORD"));
		loginUser.setNAME(request.getParameter("NAME"));
		
		if(userService.updateUser(loginUser)) {			
			System.out.println("update() 성공!");			
			view = "/User/userContents.jsp";

		} else {
			System.out.println("[ ERROR ] : UserController - update() 오류");
			
			loginUser.setPASSWORD(PASSWORD);
			loginUser.setNAME(NAME);
		}
	}
	
	private void delete() {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if(userService.deleteUser(loginUser.getUSER_CODE())) {
			System.out.println("delete() 성공!");
			request.getSession().invalidate();
			view = "/Board/boardList.jsp";
		} else {
			System.out.println("[ ERROR ] : UserController - delete() 오류");
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
