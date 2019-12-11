package com.service.user;

import java.util.ArrayList;

import com.DAO.user.UserDAO;
import com.DAO.user.UserDAOImpl;
import com.VO.UsersVO;

public class UserServiceImpl implements UserService {

	private UserDAO dao = new UserDAOImpl();

	@Override
	public ArrayList<UsersVO> selectUsersListData() {
		ArrayList<UsersVO> usersList = null;
		try {
			usersList = dao.selectUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

	@Override
	public boolean insertUser(UsersVO usersVO) {
		if (dao.insertUser(usersVO) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
