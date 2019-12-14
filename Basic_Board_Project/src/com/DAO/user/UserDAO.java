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

	// 로그인 상태 조회
	public int checkStatus(UsersVO usersVO);

	// 로그인 정보 조회
	public UsersVO selectOneUser(UsersVO usersVO);

	// 유저코드로 유저정보 가져오기
	public UsersVO selectOneUser(int USER_CODE);
}
