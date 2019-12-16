package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VO.BoardsVO;
import com.VO.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;
import com.service.board.Paging;

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

		this.request.setCharacterEncoding("utf-8");
		this.response.setContentType("text/html;charset=utf-8");

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
		case "updateReady":
			updateReady();
			break;
		case "update":
			update();
			boardContents();
			break;
		case "delete":
			delete();
			selectList();
			break;
		case "search":
			search();
			selectList();
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void search() {
		// request.setAttribute("boardList", boardService.selectBoardsListData());
		System.out.println("option : " + request.getParameter("opt"));
		System.out.println("condition : " + request.getParameter("condition"));
		if (request.getParameter("condition") == "") {
			return;
		}
		// view = "/Board/boardList.jsp";
	}

	// 게시글 리스트
	private void selectList() throws IOException {
		Paging paging = new Paging();

		int curPage = 1;

		paging.makeLastPageNum();
		paging.makeBlock(curPage);

		if (request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
			paging.makeBlock(curPage);
			request.setAttribute("curPageNum", curPage);
		}
		Integer blockStartNum = paging.getBlockStartNum();
		Integer blockLastNum = paging.getBlockLastNum();
		Integer lastPageNum = paging.getLastPageNum();

		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum);

		request.setAttribute("boardList", boardService.selectBoardsListData(curPage));

		view = "/Board/boardList.jsp";
	}

	// 게시글 등록 & 파일 첨부
	private void insertBoard() {

		// 업로드될 경로
		String filePath = "/File/Upload/";

		// 업로드될 실제 경로 (이클립스 상의 절대경로)
		String FILE_PATH = request.getSession().getServletContext().getRealPath(filePath);
		System.out.println("절대경로 : " + FILE_PATH);

		String encoding = "UTF-8";
		int maxSize = 1024 * 1024 * 5;

		MultipartRequest mr = null;

		try {

			mr = new MultipartRequest(request,
					FILE_PATH, // 파일이 저장될 폴더
					maxSize, // 최대 업로드크기 (5MB)
					encoding, // 인코딩 방식
					new DefaultFileRenamePolicy() // 동일한 파일명이 존재하면 파일명 뒤에 일련번호를 부여
			);

		} catch (IOException e1) {
			System.out.println("[ ERROR ] : BoardController - MultipartRequest 객체 생성 오류");
			e1.printStackTrace();
		}

		
		// 게시글 등록
		BoardsVO insertBoard = new BoardsVO();

		insertBoard.setUSER_CODE(Integer.parseInt(request.getParameter("USER_CODE")));
		insertBoard.setTITLE(mr.getParameter("TITLE"));
		insertBoard.setCONTEXT(mr.getParameter("CONTEXT"));

		if (boardService.insertBoard(insertBoard)) {
			System.out.println("게시물 insert 성공!");
		} else {
			System.out.println("[ ERROR ] : BoardController - insertBoard() 게시물 등록 오류");
		}

		
		// 파일 첨부
		//Enumeration<?> files = mr.getFileNames();	// 다중파일일 경우

		String FILE_ORIGINAL_NAME = null, FILE_STORED_NAME = null, FILE_SIZE = null;
		String fileType = null;
		String fileExtend = null;

		//while (files.hasMoreElements()) {

			// 실제 저장된 파일명
			FILE_STORED_NAME = mr.getFilesystemName("FILE");

			if (FILE_STORED_NAME != null) {

				// 원래 파일명
				FILE_ORIGINAL_NAME = mr.getOriginalFileName("FILE");

				// 파일 크기
				FILE_SIZE = String.valueOf(mr.getFile("FILE").length());

				// 파일 타입
				fileType = mr.getContentType("FILE");

				// 파일 확장자
				fileExtend = FILE_STORED_NAME.substring(FILE_STORED_NAME.lastIndexOf(".") + 1);
			}
		//}

		FilesVO insertFile = new FilesVO();

		if (FILE_STORED_NAME != null) {

			insertFile.setUSER_CODE(Integer.parseInt(request.getParameter("USER_CODE")));
			insertFile.setFILE_ORIGINAL_NAME(FILE_ORIGINAL_NAME);
			insertFile.setFILE_STORED_NAME(FILE_STORED_NAME);
			insertFile.setFILE_PATH(FILE_PATH);
			insertFile.setFILE_SIZE(FILE_SIZE);

			if (boardService.insertFile(insertFile)) {
				System.out.println(insertFile.toString() + "\n파일타입 : " + fileType + " 파일확장자명 : " + fileExtend);
			} else {
				System.out.println("[ ERROR ] : BoardController - insertBoard() 파일업로드 오류");
			}
		}

	}

	// 글 내용
	private void boardContents() {

		// 게시글 내용
		int BOARD_CODE = Integer.parseInt(request.getParameter("BOARD_CODE"));

		boardService.increaseCountView(BOARD_CODE);

		request.setAttribute("boardContents", boardService.selectBoardContents(BOARD_CODE).get("boardsVO"));
		request.setAttribute("userContents", boardService.selectBoardContents(BOARD_CODE).get("usersVO"));
		view = "/Board/boardContents.jsp";

		// 첨부파일 내용

	}

	private void updateReady() {
		int BOARD_CODE = Integer.parseInt(request.getParameter("BOARD_CODE"));

		request.setAttribute("boardContents", boardService.selectBoardContents(BOARD_CODE).get("boardsVO"));
		view = "/Board/updateBoard.jsp";
	}

	private void update() {
		BoardsVO updateBoard = new BoardsVO();
		updateBoard.setTITLE(request.getParameter("TITLE"));
		updateBoard.setCONTEXT(request.getParameter("CONTEXT"));
		updateBoard.setBOARD_CODE(Integer.parseInt(request.getParameter("BOARD_CODE")));

		if (boardService.updateBoard(updateBoard)) {
			System.out.println("게시물 update 성공!");
		} else {
			System.out.println("[ ERROR ] : BoardController - update() 게시물 수정 오류");
		}

	}

	private void delete() {

		if (boardService.deleteBoard(Integer.parseInt(request.getParameter("BOARD_CODE")))) {
			System.out.println("게시물 update 성공!");
		} else {
			System.out.println("[ ERROR ] : BoardController - delete() 게시물 삭제 오류");
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
