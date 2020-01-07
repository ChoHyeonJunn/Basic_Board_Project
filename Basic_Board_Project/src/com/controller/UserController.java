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
import com.utility.rsaweb.RSA;
import com.utility.rsaweb.RSAUtil;
import com.utility.sha256.SHA256_Util;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;

	private UserService userService = new UserServiceImpl();
	private RSAUtil rsaUtil = new RSAUtil();

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
		// 회원가입 페이지 요청
		case "insertUserPage":
			insertUserPage();
			break;
		// 회원가입
		case "insert":
			insertUser();
			break;
		// 로그인 페이지 요청
		case "loginPage":
			loginPage();
			break;
		// 로그인
		case "login":
			login();
			break;
		// 로그아웃
		case "logout":
			logout();
			break;
		// 회원정보 수정 페이지 요청
		case "edit":
			viewEdit();
			break;
		// 회원정보 수정
		case "update":
			update();
			break;
		// 회원탈퇴
		case "delete":
			delete();
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// 회원가입 페이지 요청
	private void insertUserPage() {
		// 기존 생성되어있는 privateKey가 있다면 session에서 파기!
		session = request.getSession();

		if (session.getAttribute("RSAprivateKey") != null)
			session.removeAttribute("RSAprivateKey");

		// 새로운 rsa 객체 생성
		RSA rsa = rsaUtil.createRSA();
		request.setAttribute("modulus", rsa.getModulus());
		request.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivateKey());

		view = "/User/insertUser.jsp";
	}

	// 회원가입
	private void insertUser() throws IOException {
		UsersVO user = new UsersVO();
		PrivateKey privateKey = null;

		session = request.getSession();

		if (session.getAttribute("RSAprivateKey") == null) {
			System.out.println("session 에 RSAprivateKey가 존재하지 않습니다!!");
			view = "/User/insertUser.jsp";
		} else {
			privateKey = (PrivateKey) session.getAttribute("RSAprivateKey");
			session.removeAttribute("RSAprivateKey");
		}

		try {
			String USERID = rsaUtil.getDecryptText(privateKey, request.getParameter("USERID"));
			String PASSWORD = rsaUtil.getDecryptText(privateKey, request.getParameter("PASSWORD"));
			String NAME = rsaUtil.getDecryptText(privateKey, request.getParameter("NAME"));

			user.setUSERID(USERID);
			user.setPASSWORD(PASSWORD);
			user.setNAME(NAME);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (userService.insertUser(user)) {
			System.out.println("USERS DB 입력 성공!");
			view = "/UserController?action=loginPage";
		} else {
			throw new IOException("USERS DB 입력 오류");
		}
	}

	// 로그인 페이지 요청
	private void loginPage() {
		// 기존 생성되어있는 privateKey가 있다면 session에서 파기!
		session = request.getSession();

		if (session.getAttribute("RSAprivateKey") != null)
			session.removeAttribute("RSAprivateKey");

		// 새로운 rsa 객체 생성
		RSA rsa = rsaUtil.createRSA();
		request.setAttribute("modulus", rsa.getModulus());
		request.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivateKey());

		view = "/User/login.jsp";
	}

	// 로그인
	private void login() {
		UsersVO requestUser = new UsersVO();
		PrivateKey privateKey = null;

		session = request.getSession();

		if (session.getAttribute("RSAprivateKey") == null) {
			System.out.println("session 에 RSAprivateKey가 존재하지 않습니다!!");
			view = "/User/insertUser.jsp";
		} else {
			privateKey = (PrivateKey) session.getAttribute("RSAprivateKey");
			session.removeAttribute("RSAprivateKey");
		}
		try {
			String USERID = rsaUtil.getDecryptText(privateKey, request.getParameter("USERID"));
			String PASSWORD = rsaUtil.getDecryptText(privateKey, request.getParameter("PASSWORD"));

			requestUser.setUSERID(USERID);
			requestUser.setPASSWORD(PASSWORD);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

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

	// 회원정보 수정 페이지 요청
	private void viewEdit() {
		// 기존 생성되어있는 privateKey가 있다면 session에서 파기!
		session = request.getSession();

		if (session.getAttribute("RSAprivateKey") != null)
			session.removeAttribute("RSAprivateKey");

		// 새로운 rsa 객체 생성
		RSA rsa = rsaUtil.createRSA();
		request.setAttribute("modulus", rsa.getModulus());
		request.setAttribute("exponent", rsa.getExponent());
		session.setAttribute("RSAprivateKey", rsa.getPrivateKey());

		view = "/User/updateUser.jsp";
	}

	// 회원정보 수정
	private void update() {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		String NEW_NAME = null;
		String NEW_PASSWORD = null;
		String OLD_PASSWORD = null;

		//////////////////// 받아온 파라미터 RSA 복호화 과정/////////////////////////
		PrivateKey privateKey = null;		
		session = request.getSession();
		
		if (session.getAttribute("RSAprivateKey") == null) {
			System.out.println("session 에 RSAprivateKey가 존재하지 않습니다!!");
			view = "/User/userContents.jsp";
		} else {
			privateKey = (PrivateKey) session.getAttribute("RSAprivateKey");
			session.removeAttribute("RSAprivateKey");
		}		

		try {
			NEW_NAME = rsaUtil.getDecryptText(privateKey, request.getParameter("NEW_NAME"));
			NEW_PASSWORD = rsaUtil.getDecryptText(privateKey, request.getParameter("NEW_PASSWORD"));
			OLD_PASSWORD = rsaUtil.getDecryptText(privateKey, request.getParameter("OLD_PASSWORD"));
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////
		
		//////////////////// 기존 비밀번호 비교 후 update /////////////////////////
		SHA256_Util sha = new SHA256_Util();
		String encrypedOLD_PW = sha.encryptSHA256(OLD_PASSWORD);

		if (!encrypedOLD_PW.equals(loginUser.getPASSWORD())) {
			msg = "비밀번호가 틀렸습니다.";
			request.setAttribute("msg", msg);

			view = "/User/loginErrorPage.jsp";
		} else {
			String NAME = loginUser.getNAME();
			String PASSWORD = loginUser.getPASSWORD();			
			
			loginUser.setNAME(NEW_NAME);
			loginUser.setPASSWORD(NEW_PASSWORD);

			if (userService.updateUser(loginUser)) {
				System.out.println("update() 성공!");
				view = "/User/userContents.jsp";

			} else {
				System.out.println("[ ERROR ] : UserController - update() 오류");

				loginUser.setNAME(NAME);
				loginUser.setPASSWORD(PASSWORD);
			}
		}
	}

	// 회원탈퇴
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
