package com.service.user;

import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.UsersVO;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();

	// 회원가입
	@Override
	public boolean insertUser(UsersVO usersVO) {
		
		if (userDAO.insertUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 로그인 처리
	@Override
	public UsersVO loginCheck(UsersVO user) {
		
		// 로그인 여부 체크
		int result = userDAO.checkStatus(user);
		
		UsersVO loginUser = new UsersVO();
		
		if(result == 1) {	// 로그인 성공
		
			loginUser = userDAO.selectOneUser(user);
			loginUser.setStatus(1);
		
		} else {
			
			if (result == 0) {	// 패스워드 오류
				loginUser.setStatus(0);
			} else {	// 아이디 오류
				loginUser.setStatus(-1);
			}
		}
		
		return loginUser;
	}

	// 한 명의 회원정보 가져오기
	@Override
	public UsersVO selectOneUser(int USER_CODE) {
		
		UsersVO userContents = userDAO.selectOneUser(USER_CODE);
		
		return userContents;
	}

	@Override
	public boolean updateUser(UsersVO usersVO) {
		
		if (userDAO.updateUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(int USER_CODE) {
		
		if (userDAO.deleteUser(USER_CODE) > 0) {
			return true;
		} else {
			return false;
		}	}
}
