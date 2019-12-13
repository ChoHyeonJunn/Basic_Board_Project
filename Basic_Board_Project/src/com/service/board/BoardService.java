package com.service.board;

import java.util.ArrayList;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardService {

	public ArrayList<BoardListVO> selectBoardsListData();
	// 게시판 글쓰기
	public int insertBoard(BoardsVO board);
}
