package com.DAO.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.UsersVO;

public class UserDAOImpl extends JDBCTemplate implements UserDAO {

	// 회원 정보 리스트
	@Override
	public ArrayList<UsersVO> selectUsers() throws Exception {

		Connection conn = getConnection();
		ArrayList<UsersVO> usersList = new ArrayList<UsersVO>();

		String sql = "SELECT * FROM USERS";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UsersVO user = new UsersVO();

				user.setUSER_CODE(rs.getInt("USER_CODE"));
				user.setUSERID(rs.getString("USERID"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setNAME(rs.getString("NAME"));

				user.setCREATE_DATE(rs.getDate("CREATE_DATE"));

				usersList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectUsers() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return usersList;
	}

	// 회원가입
	@Override
	public int insertUser(UsersVO usersVO) {

		Connection conn = getConnection();

		String sql = " INSERT INTO USERS (USER_CODE, USERID, PASSWORD, NAME) VALUES(SEQ_USERS_USER_CODE.NEXTVAL, ?, ?, ?) ";

		PreparedStatement pstmt = null;
		int res = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, usersVO.getUSERID());
			pstmt.setString(2, usersVO.getPASSWORD());
			pstmt.setString(3, usersVO.getNAME());

			res = pstmt.executeUpdate();

			if (res > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - insertUser() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return res;
	}

	// 회원 정보 수정
	@Override
	public int updateUser(UsersVO usersVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 회원 탈퇴
	@Override
	public int deleteUser(UsersVO usersVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
