package com.service.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.BoardListVO;
import com.VO.BoardsVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO = new BoardDAOImpl();
	UserDAO usersDAO = new UserDAOImpl();

	@Override
	public ArrayList<BoardListVO> selectBoardsListData(int curPage) {
		ArrayList<BoardListVO> res = new ArrayList<BoardListVO>();
		ArrayList<BoardListVO> boardsList = null;
		try {
			boardsList = boardDAO.selectBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = ((curPage * 10) - 10); i < (curPage * 10); i++) {
			if (boardsList.size() <= i)
				break;
			res.add(boardsList.get(i));
		}

		return res;
	}

	@Override
	public int insertBoard(BoardsVO board) {
		return boardDAO.insertBoard(board);
	}

	@Override
	public Map<String, Object> selectBoardContents(int BOARD_CODE) {
		Map<String, Object> contentsMap = new HashMap<String, Object>();
		
		contentsMap.put("boardsVO", boardDAO.selectBoardContents(BOARD_CODE));
		contentsMap.put("usersVO", usersDAO.selectOneUser(boardDAO.selectBoardContents(BOARD_CODE).getUSER_CODE()));
		
		return contentsMap;
	}

	@Override
	public void increaseCountView(int BOARD_CODE) {
		boardDAO.increaseCountView(BOARD_CODE);
	}

	@Override
	public void updateBoard(BoardsVO board) {
		boardDAO.updateBoard(board);
	}

	@Override
	public void deleteBoard(int BOARD_CODE) {
		boardDAO.deleteBoard(BOARD_CODE);
	}

}
