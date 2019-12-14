package com.service.user;

import com.VO.UsersVO;

public interface UserService {
	
	// 회원 가입
	public boolean insertUser(UsersVO usersVO);

	// 로그인 처리
	public UsersVO loginCheck(UsersVO user);
	
	// 한 명의 회원정보 가져오기
	public UsersVO selectOneUser(int USER_CODE);
	
	// 회원정보 수정
	public boolean updateUser(UsersVO usersVO);
	
	// 회원 탈퇴
	public boolean deleteUser(int USER_CODE);
}
