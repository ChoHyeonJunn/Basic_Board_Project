package com.service.board;

import java.util.ArrayList;
import java.util.Map;

import com.VO.BoardListVO;
import com.VO.BoardsVO;
import com.VO.FilesVO;

public interface BoardService {
	// 게시판 기본 내용
	public ArrayList<BoardListVO> selectBoardsListData(int curPage);

	// 게시판 기본 내용 - 검색일 경우
	public ArrayList<BoardListVO> selectSearchListData(int curPage, int how, String kwd);

	// 게시판 글쓰기
	public boolean insertBoard(BoardsVO board);

	// 게시판 내용
	public Map<String, Object> selectBoardContents(int BOARD_CODE);

	// 조회수 올리기
	public void increaseCountView(int BOARD_CODE);

	// 게시판 수정
	public boolean updateBoard(BoardsVO board);
	
	// 게시물 삭제
	public boolean deleteBoard(int BOARD_CODE);
	
	// 첨부파일 업로드
	public boolean insertFile(FilesVO file);

	// 게시물 별 댓글 수 
	public void increaseCountComment(int BOARD_CODE);
	public void decreaseCountComment(int BOARD_CODE);

	
	// FILE_CODE로 첨부파일 내용 가져오기
	public FilesVO selectOneFile(int FILE_CODE);

	// 어떤 게시물의 첨부파일 내용
	public FilesVO selectFileContents(int BOARD_CODE);
	
	// 어떤 게시글의 첨부파일 삭제하기
	public boolean deleteFile(int FILE_CODE);
}
