package com.service;

import java.util.ArrayList;

import com.DAO.BoardDAO;
import com.DAO.BoardDAOImpl;
import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO dao = new BoardDAOImpl();

	@Override
	public ArrayList<BoardsVO> selectBoardsListData() {
		ArrayList<BoardsVO> boardsList = null;
		try {
			boardsList = dao.selectBoards();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardsList;
	}

	@Override
	public ArrayList<CommentsVO> selectCommentsListData() {
		ArrayList<CommentsVO> commentsList = null;
		try {
			commentsList = dao.selectComments();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentsList;
	}

	@Override
	public ArrayList<UsersVO> selectUsersListData() {
		ArrayList<UsersVO> usersList = null;
		try {
			usersList = dao.selectUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public ArrayList<FilesVO> selectFilesListData() {
		ArrayList<FilesVO> filesList = null;
		try {
			filesList = dao.selectFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filesList;
	}

	@Override
	public boolean insertUser(UsersVO usersVO) {
		if(dao.insertUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
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
