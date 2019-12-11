package com.DAO.user;

import java.util.ArrayList;

import com.VO.UsersVO;

public interface UserDAO {

	public ArrayList<UsersVO> selectUsers() throws Exception;

	// 회원가입
	public int insertUser(UsersVO usersVO);

	// 회원정보 수정
	public int updateUser(UsersVO usersVO);

	// 회원정보 삭제
	public int deleteUser(UsersVO usersVO);
}
