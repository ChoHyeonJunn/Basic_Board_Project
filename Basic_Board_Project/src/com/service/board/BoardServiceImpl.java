package com.service.board;

import java.util.ArrayList;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.VO.BoardListVO;
import com.VO.BoardsVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO = new BoardDAOImpl();

	@Override
	public ArrayList<BoardListVO> selectBoardsListData() {
		ArrayList<BoardListVO> boardsList = null;
		try {
			boardsList = boardDAO.selectBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardsList;
	}

	@Override
	public int insertBoard(BoardsVO board) {
		return boardDAO.insertBoard(board);
	}

	@Override
	public BoardsVO selectBoardContents(int BOARD_CODE) {
		return boardDAO.selectBoardContents(BOARD_CODE);
	}

	@Override
	public void increaseCountView(int BOARD_CODE) {
		boardDAO.increaseCountView(BOARD_CODE);		
	}

}
