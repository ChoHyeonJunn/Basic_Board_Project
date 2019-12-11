package com.DAO;

import java.util.ArrayList;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;
import com.VO.UsersVO;

public interface BoardDAO {

	// 전체 리스트 출력
	public ArrayList<BoardsVO> selectBoards() throws Exception;
	public ArrayList<CommentsVO> selectComments() throws Exception;
	public ArrayList<UsersVO> selectUsers() throws Exception;	
	public ArrayList<FilesVO> selectFiles() throws Exception;
	
	// 회원가입
	public int insertUser(UsersVO usersVO);
	
	// 회원정보 수정
	public int updateUser(UsersVO usersVO);
	
	// 회원정보 삭제
	public int deleteUser(UsersVO usersVO);
}
