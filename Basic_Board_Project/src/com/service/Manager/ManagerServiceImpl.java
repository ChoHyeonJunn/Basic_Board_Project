package com.service.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;
import com.DAO.comment.CommentDAO;
import com.DAO.comment.CommentDAOImpl;
import com.DAO.file.FileDAO;
import com.DAO.file.FileDAOImpl;
import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public class ManagerServiceImpl implements ManagerService {

	BoardDAO boardDAO = new BoardDAOImpl();
	UserDAO userDAO = new UserDAOImpl();
	FileDAO fileDAO = new FileDAOImpl();
	CommentDAO commentDAO = new CommentDAOImpl();

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
			commentsList = commentDAO.selectComments();
			filesList = fileDAO.selectFiles();
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
	public ArrayList<UsersVO> selectUsersListData() {
		ArrayList<UsersVO> usersList = null;
		try {
			usersList = userDAO.selectUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

}
