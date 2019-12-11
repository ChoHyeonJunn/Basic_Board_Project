package com.service.user;

import java.util.ArrayList;

import com.VO.UsersVO;

public interface UserService {

	public ArrayList<UsersVO> selectUsersListData();

	public boolean insertUser(UsersVO usersVO);
}
