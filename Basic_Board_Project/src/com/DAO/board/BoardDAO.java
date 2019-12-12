package com.DAO.board;

import java.util.ArrayList;

import com.VO.BoardsVO;

public interface BoardDAO {

	public ArrayList<BoardsVO> selectBoards() throws Exception;

}
