package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.board.BoardService;
import com.service.board.BoardServiceImpl;

@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int FILE_CODE = Integer.parseInt(request.getParameter("FILE_CODE"));

		String FILE_STORED_NAME = boardService.selectOneFile(FILE_CODE).getFILE_STORED_NAME();
		System.out.println(FILE_STORED_NAME);
		String FILE_PATH = this.getServletContext().getRealPath("/File/Upload/");
		System.out.println(FILE_PATH);
		File downloadFile = new File(FILE_PATH + "/" + FILE_STORED_NAME);
		System.out.println(downloadFile);

		String mimeType = getServletContext().getMimeType(downloadFile.toString());

		if (mimeType == null) {
			response.setContentType("application/octet-stream");
		}

		String downloadName = null;

		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			try {
				downloadName = new String(FILE_STORED_NAME.getBytes("UTF-8"), "8859_1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				downloadName = new String(FILE_STORED_NAME.getBytes("EUC-KR"), "8859_1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(downloadName);

		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName + "\";");

		FileInputStream in = null;
		ServletOutputStream out = null;

		// out.clear();

		byte b[] = new byte[1024];
		int data = 0;

		try {
			in = new FileInputStream(downloadFile);
			out = response.getOutputStream();

			while ((data = (in.read(b, 0, b.length))) != -1) {
				out.write(b, 0, data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			out.flush();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
