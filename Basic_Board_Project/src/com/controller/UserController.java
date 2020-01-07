package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VO.UsersVO;
import com.service.user.UserService;
import com.service.user.UserServiceImpl;
import com.utility.rsa.RSA;
import com.utility.rsa.RSAUtil;
import com.utility.sha.SHA256_Util;

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
		
		// 회원가입 화면 진입 (RSA키 생성을 위함)
		case "insertForm":
			insertUserForm();
			break;

		// 회원가입
		case "insert":
			insertUser();
			break;

		// 로그인 화면 진입 (RSA키 생성을 위함)
		case "loginForm":
			loginForm();
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
	
	RSAUtil rsaUtil = new RSAUtil();

	// 회원가입 화면 진입 (RSA키 생성을 위함)
	private void insertUserForm() {
		
		session = request.getSession();
		
		// 기존 key 파기
		if(session.getAttribute("RSAprivateKey") != null)
			session.removeAttribute("RSAprivateKey");
		
		RSA rsa = rsaUtil.createRSA();
		
		request.setAttribute("modulus", rsa.getModulus());
		request.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivateKey());
		
		view = "/User/insertUser.jsp";
	}

	SHA256_Util shaUtil = new SHA256_Util();
	// 회원가입
	private void insertUser() throws IOException {
		
		UsersVO user = new UsersVO();
		PrivateKey privateKey = null;
		
		String requestUSERID = request.getParameter("USERID");
		String requestPASSWORD = request.getParameter("PASSWORD");
		String requestNAME = request.getParameter("NAME");
		
		System.out.println("복호화 전 USERID : " + requestUSERID);
		System.out.println("복호화 전 PASSWORD : " + requestPASSWORD);
		System.out.println("복호화 전 NAME : " + requestNAME);
		
		if(session.getAttribute("RSAprivateKey") == null) {
			System.out.println("session에 RSAprivateKey가 존재하지 않습니다.");
			view = "/User/insertUser.jsp";
		} else {
			// 개인키 취득
			privateKey = (PrivateKey) session.getAttribute("RSAprivateKey");
			// session에 저장된 개인키 초기화
			session.removeAttribute("RSAprivateKey");
		}
		
		String USERID = null;
		String PASSWORD = null;
		String NAME = null;
		
		// 복호화
		try {
			USERID = rsaUtil.getDecryptText(privateKey, requestUSERID);
			PASSWORD = rsaUtil.getDecryptText(privateKey, requestPASSWORD);
			NAME = rsaUtil.getDecryptText(privateKey, requestNAME);
			
			user.setUSERID(USERID);
			user.setPASSWORD(shaUtil.encryptSHA256(PASSWORD));	// SHA로 다시 암호화해서 DB 저장
			user.setNAME(NAME);
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("복호화 후 USERID : " + USERID);
		System.out.println("복호화 후 PASSWORD : " + PASSWORD);
		System.out.println("복호화 후 NAME : " + NAME);

		if (userService.insertUser(user)) {
			System.out.println("USERS DB 입력 성공!");
			view = "/UserController?action=loginForm";
		} else {
			throw new IOException("USERS DB 입력 오류");
		}
	}
	
	// 로그인 화면 진입 (RSA키 생성을 위함)
	private void loginForm() {
		
		session = request.getSession();
		
		// 기존 key 파기
		if(session.getAttribute("RSAprivateKey") != null)
			session.removeAttribute("RSAprivateKey");
		
		RSA rsa = rsaUtil.createRSA();
		
		request.setAttribute("modulus", rsa.getModulus());
		request.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivateKey());
		
		view = "/User/login.jsp";
	}
	
	// 로그인 처리
	private void login() {

		UsersVO requestUser = new UsersVO();
		PrivateKey privateKey = null;
		
		String requestUSERID = request.getParameter("USERID");
		String requestPASSWORD = request.getParameter("PASSWORD");
		
		System.out.println("복호화 전 USERID : " + requestUSERID);
		System.out.println("복호화 전 PASSWORD : " + requestPASSWORD);
		
		if(session.getAttribute("RSAprivateKey") == null) {
			System.out.println("session에 RSAprivateKey가 존재하지 않습니다.");
			view = "/User/login.jsp";
		} else {
			// 개인키 취득
			privateKey = (PrivateKey) session.getAttribute("RSAprivateKey");
			// session에 저장된 개인키 초기화
			session.removeAttribute("RSAprivateKey");
		}
		
		String USERID = null;
		String PASSWORD = null;
		
		// 복호화
		try {
			USERID = rsaUtil.getDecryptText(privateKey, requestUSERID);
			PASSWORD = rsaUtil.getDecryptText(privateKey, requestPASSWORD);

			requestUser.setUSERID(USERID);
			requestUser.setPASSWORD(shaUtil.encryptSHA256(PASSWORD));	// sha로 다시 암호화
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("복호화 후 USERID : " + USERID);
		System.out.println("복호화 후 PASSWORD : " + PASSWORD);
		
		UsersVO loginUser = userService.loginCheck(requestUser);

			if (loginUser.getStatus() == 1) { // 로그인 성공

			view = "/BoardController?action=listBoard"; // 다시 BoardController로 액션 보내기!
			
			session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);

		} else { // 로그인 실패

			if (loginUser.getStatus() == 0) { // 패스워드 오류
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
		// UsersVO userVO =
		// userService.selectOneUser(Integer.parseInt(request.getParameter("USER_CODE")));
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (!request.getParameter("PASSWORD").equals(loginUser.getPASSWORD())) {
			msg = "비밀번호가 틀렸습니다.";
			request.setAttribute("msg", msg);

			view = "/User/loginErrorPage.jsp";
		} else {
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

		if (userService.updateUser(loginUser)) {
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

		if (userService.deleteUser(loginUser.getUSER_CODE())) {
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
