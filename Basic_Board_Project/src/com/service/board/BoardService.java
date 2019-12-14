package com.service.board;

import java.util.ArrayList;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardService {

	public ArrayList<BoardListVO> selectBoardsListData(int curPage);
	// 게시판 글쓰기
	public int insertBoard(BoardsVO board);
	
	// 게시판 내용
	public BoardsVO selectBoardContents(int BOARD_CODE);
	
	// 조회수 올리기
	public void increaseCountView(int BOARD_CODE);
}
