package com.DAO.file;

import java.util.ArrayList;

import com.VO.FilesVO;

public interface FileDAO {

	public ArrayList<FilesVO> selectFiles() throws Exception;

	public int insertFile(FilesVO file);
	
	// 어떤 게시글의 첨부파일 가져오기
	public FilesVO selectFileContent(int FILE_CODE);
}
