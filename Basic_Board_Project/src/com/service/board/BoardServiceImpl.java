package com.service.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.DAO.file.FileDAO;
import com.DAO.file.FileDAOImpl;
import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.BoardListVO;
import com.VO.BoardsVO;
import com.VO.FilesVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO = new BoardDAOImpl();
	UserDAO usersDAO = new UserDAOImpl();
	FileDAO fileDAO = new FileDAOImpl();

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
	public boolean insertBoard(BoardsVO board) {
		
		if(boardDAO.insertBoard(board) > 0) {
			return true;
		} else {
			return false;
		}
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
	public boolean updateBoard(BoardsVO board) {
		
		if(boardDAO.updateBoard(board) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBoard(int BOARD_CODE) {
		
		if(boardDAO.deleteBoard(BOARD_CODE) > 0) {
			return true;
		} else {
			return false;
		}
	}
	

	// 첨부파일 업로드
	@Override
	public boolean insertFile(FilesVO file) {
		
		if(fileDAO.insertFile(file) > 0) {
			return true;
		} else {
			return false;
		}
	}

}
