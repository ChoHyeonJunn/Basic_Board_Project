package com.service.comment;

import java.util.ArrayList;

import com.VO.CommentsVO;

public interface CommentService {
	// 댓글 리스트 - for Manager
	public ArrayList<CommentsVO> selectCommentsListData();
	
	// 게시글 별 댓글 리스트
	public ArrayList<CommentsVO> selectComments(int BOARD_CODE);
	
	// 원 댓글 달기
	public int insertComment(CommentsVO comment);
		
	// 원 댓글의 대댓글 달기
	public int insertComment(CommentsVO parentComment, CommentsVO subComment);
	
	// 댓글 수정
	public int updateComment(CommentsVO comment);

	// 댓글 삭제
	public boolean deleteComment(int COMMENT_CODE, int GROUP_DEPTH, int GROUP_NO);
	
	// 좋아요
	public int goodComment(int COMMENT_CODE);
	
	// 싫어요
	public int badComment(int COMMENT_CODE);
}
