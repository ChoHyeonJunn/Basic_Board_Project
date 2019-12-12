package com.service.comment;

import java.util.ArrayList;

import com.DAO.comment.CommentDAO;
import com.DAO.comment.CommentDAOImpl;
import com.VO.CommentsVO;

public class CommentServiceImpl implements CommentService {
	CommentDAO commentDAO = new CommentDAOImpl();

	@Override
	public ArrayList<CommentsVO> selectCommentsListData() {
		ArrayList<CommentsVO> commentsList = null;
		try {
			commentsList = commentDAO.selectComments();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentsList;
	}

}
