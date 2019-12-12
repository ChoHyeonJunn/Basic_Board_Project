package com.service.user;

import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.UsersVO;

public class UserServiceImpl implements UserService {

	private UserDAO dao = new UserDAOImpl();

	// 회원가입
	@Override
	public boolean insertUser(UsersVO usersVO) {
		if (dao.insertUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 로그인 처리
	@Override
	public UsersVO loginCheck(UsersVO user) {
		
		// 로그인 여부 체크
		int result = dao.checkStatus(user);
		
		UsersVO loginUser = new UsersVO();
		
		if(result == 1) {	// 로그인 성공
		
			loginUser = dao.selectOneUser(user);
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
}
