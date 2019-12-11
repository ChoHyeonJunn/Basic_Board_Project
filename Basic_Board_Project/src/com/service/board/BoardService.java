package com.service.board;

import java.util.ArrayList;
import java.util.Map;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;

public interface BoardService {

	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList> selectAllData();

	public ArrayList<BoardsVO> selectBoardsListData();

	public ArrayList<CommentsVO> selectCommentsListData();

	public ArrayList<FilesVO> selectFilesListData();

	public void viewEdit();

	public void updateData();

	public void deleteData();
}
