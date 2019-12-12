package com.service.user;

import com.VO.UsersVO;

public interface UserService {
	
	// 회원 가입
	public boolean insertUser(UsersVO usersVO);

	// 로그인 처리
	public UsersVO loginCheck(UsersVO user);
}
