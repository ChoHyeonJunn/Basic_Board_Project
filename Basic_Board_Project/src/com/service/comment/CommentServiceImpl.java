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
	public int insertComment(CommentsVO parentComment, CommentsVO subComment) {
		return commentDAO.insertComment(parentComment, subComment);
		
	}

	@Override
	public int updateComment(CommentsVO comment) {
		return commentDAO.updateComment(comment);
	}

	@Override
	public boolean deleteComment(int COMMENT_CODE, int GROUP_DEPTH, int GROUP_NO) {
		
		// 댓글(대댓글) 삭제 적합성 판단 (내 DEPTH와 내가 속한 GROUP의 MAX(DEPTH)가 같다면 삭제 가능)
		if(commentDAO.maxDepth(GROUP_NO) == GROUP_DEPTH) {
			commentDAO.deleteComment(COMMENT_CODE);
			return true;
		} else {
			return false;
		}
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
