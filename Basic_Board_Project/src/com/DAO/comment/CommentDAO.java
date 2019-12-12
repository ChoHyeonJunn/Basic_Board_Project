package com.DAO.comment;

import java.util.ArrayList;

import com.VO.CommentsVO;

public interface CommentDAO {

	public ArrayList<CommentsVO> selectComments() throws Exception;
}
