package com.service;

import java.util.ArrayList;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public interface BoardService {

	public ArrayList<BoardsVO> selectBoardsListData();

	public ArrayList<CommentsVO> selectCommentsListData();

	public ArrayList<UsersVO> selectUsersListData();

	public ArrayList<FilesVO> selectFilesListData();

	public void insertData();

	public void viewEdit();

	public void updateData();

	public void deleteData();
}
