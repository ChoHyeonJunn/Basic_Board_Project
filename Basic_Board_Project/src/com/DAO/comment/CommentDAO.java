package com.DAO.comment;

import java.util.ArrayList;

import com.VO.CommentsVO;

public interface CommentDAO {

	// 댓글 리스트 - for Manager
	public ArrayList<CommentsVO> selectComments() throws Exception;
	
	// 게시글 별 댓글 리스트
	public ArrayList<CommentsVO> selectComments(int BOARD_CODE);
	
	// 원 댓글 달기
	public int insertComment(CommentsVO comment);
	
	// 원 댓글의 대댓글 달기
	public int insertComment(CommentsVO parentComment, CommentsVO subComment);
	
	// 댓글 수정
	public int updateComment(CommentsVO comment);
	
	// 댓글(대댓글) 삭제 적합성 판단 (내 DEPTH와 내가 속한 GROUP의 MAX(DEPTH)가 같다면 삭제 가능)
	public int maxDepth(int GROUP_NO);
	
	// 댓글 삭제
	public int deleteComment(int COMMENT_CODE);
	
	// 좋아요
	public int goodComment(int COMMENT_CODE);
	// 싫어요
	public int badComment(int COMMENT_CODE);
}
