package com.service.comment;

import java.util.ArrayList;

import com.VO.CommentsVO;

public interface CommentService {
	// 댓글 리스트 - for Manager
	public ArrayList<CommentsVO> selectCommentsListData();
	
	// 게시글 별 댓글 리스트
	public ArrayList<CommentsVO> selectComments(int BOARD_CODE);
	
	// 댓글 달기
	public int insertComment(CommentsVO comment);
	
	// 댓글 수정
	public int updateComment(CommentsVO comment);
	
	// 댓글 삭제
	public int deleteComment(int COMMENT_CODE);
	
	// 좋아요
	public int goodComment(int COMMENT_CODE);
	
	// 싫어요
	public int badComment(int COMMENT_CODE);
}
