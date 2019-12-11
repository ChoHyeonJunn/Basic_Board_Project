package com.DAO.board;

import java.util.ArrayList;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;

public interface BoardDAO {

	public ArrayList<BoardsVO> selectBoards() throws Exception;

	public ArrayList<CommentsVO> selectComments() throws Exception;

	public ArrayList<FilesVO> selectFiles() throws Exception;

}
