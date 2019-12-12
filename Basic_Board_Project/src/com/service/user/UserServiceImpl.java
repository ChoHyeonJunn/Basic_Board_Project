package com.service.user;

import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.UsersVO;

public class UserServiceImpl implements UserService {

	private UserDAO dao = new UserDAOImpl();

	@Override
	public boolean insertUser(UsersVO usersVO) {
		if (dao.insertUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
