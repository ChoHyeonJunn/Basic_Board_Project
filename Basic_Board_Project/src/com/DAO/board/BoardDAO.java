package com.DAO.board;

import java.util.ArrayList;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardDAO {

	// 게시판 테이블 select
	public ArrayList<BoardsVO> selectBoards() throws Exception;

	// 게시판, 유저 테이블 select
	public ArrayList<BoardListVO> selectBoardList() throws Exception;

	// 게시판 글쓰기
	public int insertBoard(BoardsVO board);

}
