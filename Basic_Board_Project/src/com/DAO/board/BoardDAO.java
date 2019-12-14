package com.DAO.board;

import java.util.ArrayList;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardDAO {

	// 게시판 테이블 select - for Manager
	public ArrayList<BoardsVO> selectBoards() throws Exception;

	// 게시판, 유저 테이블 select
	public ArrayList<BoardListVO> selectBoardList() throws Exception;

	// 게시물 내용
	public BoardsVO selectBoardContents(int BOARD_CODE);

	// 게시물 쓰기
	public int insertBoard(BoardsVO board);

	// 게시물 수정
	public int updateBoard(BoardsVO board);
	
	// 게시물 삭제
	public int deleteBoard(int BOARD_CODE);
	
	/////////////////////////////////////////////////////////////////////////
	// 조회수 올리기
	public void increaseCountView(int BOARD_CODE);

	// 게시물 총 갯수 리턴
	public int getCount();
	public int getCount(int how, String kwd);

}
