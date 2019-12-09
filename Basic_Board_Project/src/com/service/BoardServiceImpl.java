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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CommentsVO> selectCommentsListData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UsersVO> selectUsersListData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FilesVO> selectFilesListData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertData() {
		// TODO Auto-generated method stub

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
