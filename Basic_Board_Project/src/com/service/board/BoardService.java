package com.service.board;

import java.util.ArrayList;
import java.util.Map;

import com.VO.BoardListVO;
import com.VO.BoardsVO;

public interface BoardService {

	public ArrayList<BoardListVO> selectBoardsListData(int curPage);
	// 게시판 글쓰기
	public int insertBoard(BoardsVO board);
	
	// 게시판 내용
	public Map<String, Object> selectBoardContents(int BOARD_CODE);
	
	// 조회수 올리기
	public void increaseCountView(int BOARD_CODE);
	
	// 게시판 수정
	public void updateBoard(BoardsVO board);
	
	// 게시물 삭제
	public void deleteBoard(int BOARD_CODE);
}
