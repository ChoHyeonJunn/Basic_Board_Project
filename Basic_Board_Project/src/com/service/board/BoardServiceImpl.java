package com.service.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO = new BoardDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, ArrayList> selectAllData() {
		Map<String, ArrayList> selectAllDataMap = new HashMap<String, ArrayList>();

		ArrayList<BoardsVO> boardsList = null;
		ArrayList<CommentsVO> commentsList = null;
		ArrayList<FilesVO> filesList = null;
		ArrayList<UsersVO> usersList = null;

		try {
			boardsList = boardDAO.selectBoards();
			commentsList = boardDAO.selectComments();
			filesList = boardDAO.selectFiles();
			usersList = userDAO.selectUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectAllDataMap.put("boardsList", boardsList);
		selectAllDataMap.put("commentsList", commentsList);
		selectAllDataMap.put("filesList", filesList);
		selectAllDataMap.put("usersList", usersList);

		return selectAllDataMap;
	}

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

	@Override
	public ArrayList<CommentsVO> selectCommentsListData() {
		ArrayList<CommentsVO> commentsList = null;
		try {
			commentsList = boardDAO.selectComments();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentsList;
	}

	@Override
	public ArrayList<FilesVO> selectFilesListData() {
		ArrayList<FilesVO> filesList = null;
		try {
			filesList = boardDAO.selectFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filesList;
	}

	@Override
	public void viewEdit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData() {
		// TODO Auto-generated method stub

	}

}
