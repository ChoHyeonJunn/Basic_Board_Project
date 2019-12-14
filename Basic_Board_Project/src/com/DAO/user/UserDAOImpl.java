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
			System.out.println("[ ERROR ] : UserDAOImpl - selectUsers() SQL 확인하세요.");
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
			System.out.println("[ ERROR ] : UserDAOImpl - insertUser() SQL 확인하세요.");
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
		Connection conn = getConnection();

		String sql = "UPDATE USERS SET PASSWORD = ?, NAME = ? WHERE USER_CODE = ?";
		PreparedStatement pstmt = null;

		int res = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, usersVO.getPASSWORD());
			pstmt.setString(2, usersVO.getNAME());
			pstmt.setInt(3, usersVO.getUSER_CODE());

			res = pstmt.executeUpdate();

			if (res > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : UserDAOImpl - updateUser() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return res;
	}

	// 회원 탈퇴
	@Override
	public int deleteUser(int USER_CODE) {
		Connection conn = getConnection();

		String sql = "DELETE FROM USERS WHERE USER_CODE = ?";
		PreparedStatement pstmt = null;

		int res = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, USER_CODE);

			res = pstmt.executeUpdate();

			if (res > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : UserDAOImpl - deleteUser() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return res;
	}

	// 로그인 상태 조회
	@Override
	public int checkStatus(UsersVO usersVO) {

		Connection conn = getConnection();

		String sql = " SELECT * FROM USERS WHERE USERID = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1; // 실패했을 때를 위한 초기화

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usersVO.getUSERID());
			rs = pstmt.executeQuery();

			String USERID = "", PASSWORD = "";

			if (rs.next()) {
				USERID = rs.getString("USERID");
				PASSWORD = rs.getString("PASSWORD");
			}

			if (usersVO.getUSERID().equals(USERID) && usersVO.getPASSWORD().equals(PASSWORD)) {
				// 로그인 성공
				res = 1;
			} else if (usersVO.getUSERID().equals(USERID) && !usersVO.getPASSWORD().equals(PASSWORD)) {
				// 패스워드 오류
				res = 0;
			} else {
				// 아이디 존재하지 않음
				res = -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return res;
	}

	// 로그인 정보 조회
	@Override
	public UsersVO selectOneUser(UsersVO usersVO) {

		Connection conn = getConnection();

		String sql = " SELECT * FROM USERS WHERE USERID = ? AND PASSWORD = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsersVO user = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usersVO.getUSERID());
			pstmt.setString(2, usersVO.getPASSWORD());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new UsersVO();

				user.setUSER_CODE(rs.getInt("USER_CODE"));
				user.setUSERID(rs.getString("USERID"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setNAME(rs.getString("NAME"));
				user.setCREATE_DATE(rs.getDate("CREATE_DATE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	// 한 명의 회원정보 가져오기
	@Override
	public UsersVO selectOneUser(int USER_CODE) {

		Connection conn = getConnection();

		String sql = " SELECT * FROM USERS WHERE USER_CODE = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsersVO user = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_CODE);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new UsersVO();

				user.setUSER_CODE(rs.getInt("USER_CODE"));
				user.setUSERID(rs.getString("USERID"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setNAME(rs.getString("NAME"));
				user.setCREATE_DATE(rs.getDate("CREATE_DATE"));
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : UserDAOImpl - selectOneUser(int USER_CODE) SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return user;
	}

}
