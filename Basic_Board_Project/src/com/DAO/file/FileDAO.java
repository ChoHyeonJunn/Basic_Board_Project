package com.DAO.file;

import java.util.ArrayList;

import com.VO.FilesVO;

public interface FileDAO {

	public ArrayList<FilesVO> selectFiles() throws Exception;

	public int insertFile(FilesVO file);
	
	// FILE_CODE로 첨부파일 내용 가져오기
	public FilesVO selectOneFile(int FILE_CODE);
	
	// 어떤 게시글의 첨부파일 가져오기
	public FilesVO selectFileContents(int BOARD_CODE);
	
	// 어떤 게시글의 첨부파일 삭제
	public int deleteFile(int FILE_CODE);
}
