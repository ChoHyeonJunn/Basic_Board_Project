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

	@Override
	public ArrayList<CommentsVO> selectComments(int BOARD_CODE) {
		
		return commentDAO.selectComments(BOARD_CODE);
	}

	@Override
	public int insertComment(CommentsVO comment) {
		return commentDAO.insertComment(comment);
	}

	@Override
	public int updateComment(CommentsVO comment) {
		return commentDAO.updateComment(comment);
	}

	@Override
	public int deleteComment(int COMMENT_CODE) {
		return commentDAO.deleteComment(COMMENT_CODE);
	}

	@Override
	public int goodComment(int COMMENT_CODE) {
		return commentDAO.goodComment(COMMENT_CODE);
	}

	@Override
	public int badComment(int COMMENT_CODE) {
		return commentDAO.badComment(COMMENT_CODE);
	}

}
