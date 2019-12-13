package com.DAO.board;

import java.util.ArrayList;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardDAO {

	// 게시판 테이블 select - for Manager
	public ArrayList<BoardsVO> selectBoards() throws Exception;

	// 게시판, 유저 테이블 select
	public ArrayList<BoardListVO> selectBoardList() throws Exception;

	// 게시판 글쓰기
	public int insertBoard(BoardsVO board);
	
	// 게시판 내용
	public BoardsVO selectBoardContents(int BOARD_CODE);
	
	// 조회수 올리기
	public void increaseCountView(int BOARD_CODE);

}
