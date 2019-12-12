package com.service.board;

import java.util.ArrayList;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.VO.BoardsVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO = new BoardDAOImpl();

	@Override
	public ArrayList<BoardsVO> selectBoardsListData() {
		ArrayList<BoardsVO> boardsList = null;
		try {
			boardsList = boardDAO.selectBoards();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardsList;
	}

}
