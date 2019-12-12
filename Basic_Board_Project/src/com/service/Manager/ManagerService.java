package com.service.Manager;

import java.util.ArrayList;
import java.util.Map;

import com.VO.UsersVO;

public interface ManagerService {

	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList> selectAllData();

	public ArrayList<UsersVO> selectUsersListData();
}
