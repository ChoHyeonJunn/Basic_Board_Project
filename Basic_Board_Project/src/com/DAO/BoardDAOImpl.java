package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.UsersVO;

public class BoardDAOImpl implements BoardDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";

	String user = "board";
	String pw = "board";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<BoardsVO> selectBoards() throws Exception {
		connect();
		ArrayList<BoardsVO> boardsList = new ArrayList<BoardsVO>();

		String sql = "SELECT * FROM BOARDS";

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardsVO board = new BoardsVO();

				board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				board.setUSER_CODE(rs.getInt("USER_CODE"));
				board.setTITLE(rs.getString("TITLE"));
				board.setCONTEXT(rs.getString("CONTEXT"));
				board.setCOUNT_VIEW(rs.getInt("COUNT_VIEW"));
				board.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				board.setUPDATE_DATE(rs.getDate("UPDATE_DATE"));

				boardsList.add(board);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return boardsList;
	}

	@Override
	public ArrayList<CommentsVO> selectComments() throws Exception {
		connect();
		ArrayList<CommentsVO> commentsList = new ArrayList<CommentsVO>();

		String sql = "SELECT * FROM COMMENTS";

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentsVO comment = new CommentsVO();

				comment.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				comment.setUSER_CODE(rs.getInt("USER_CODE"));
				comment.setCONTEXT(rs.getString("CONTEXT"));
				comment.setCOUNT_GOOD(rs.getInt("COUNT_GOOD"));
				comment.setCOUNT_BAD(rs.getInt("COUNT_BAD"));
				comment.setCREATE_DATE(rs.getDate("CREATE_DATE"));

				commentsList.add(comment);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return commentsList;
	}

	@Override
	public ArrayList<UsersVO> selectUsers() throws Exception {
		connect();
		ArrayList<UsersVO> usersList = new ArrayList<UsersVO>();

		String sql = "SELECT * FROM USERS";

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UsersVO user = new UsersVO();

				user.setUSER_CODE(rs.getInt("USER_CODE"));
				user.setUSERID(rs.getString("USERID"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setNAME(rs.getString("NAME"));
				user.setCREATE_DATE(rs.getDate("CREATE_DATE"));

				usersList.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return usersList;
	}

}
