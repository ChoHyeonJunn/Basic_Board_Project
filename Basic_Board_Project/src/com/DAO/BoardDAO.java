package com.DAO;

import java.util.ArrayList;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public interface BoardDAO {
	public void connect();
	public void disconnect();

	public ArrayList<BoardsVO> selectBoards() throws Exception;
	public ArrayList<CommentsVO> selectComments() throws Exception;
	public ArrayList<UsersVO> selectUsers() throws Exception;	
	public ArrayList<FilesVO> selectFiles() throws Exception;
}
