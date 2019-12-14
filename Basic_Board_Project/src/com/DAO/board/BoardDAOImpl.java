package com.DAO.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.BoardListVO;
import com.VO.BoardsVO;

public class BoardDAOImpl extends JDBCTemplate implements BoardDAO {

	@Override
	public ArrayList<BoardsVO> selectBoards() throws Exception {

		Connection conn = getConnection();
		ArrayList<BoardsVO> boardsList = new ArrayList<BoardsVO>();

		String sql = "SELECT * FROM BOARDS";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardsVO board = new BoardsVO();

				board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				board.setUSER_CODE(rs.getInt("USER_CODE"));
				board.setTITLE(rs.getString("TITLE"));
				board.setCONTEXT(rs.getString("CONTEXT"));

				board.setCOUNT_VIEW(rs.getInt("COUNT_VIEW"));
				board.setCOUNT_COMMENT(rs.getInt("COUNT_COMMENT"));
				board.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				board.setUPDATE_DATE(rs.getDate("UPDATE_DATE"));

				boardsList.add(board);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectBoards() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return boardsList;
	}

	@Override
	public ArrayList<BoardListVO> selectBoardList() throws Exception {

		Connection conn = getConnection();

		ArrayList<BoardListVO> boardList = new ArrayList<BoardListVO>();

		String sql = " SELECT U.USER_CODE, U.USERID, U.PASSWORD, U.NAME, U.CREATE_DATE, "
				+ "        B.BOARD_CODE, B.USER_CODE, B.TITLE, B.CONTEXT, B.COUNT_VIEW, B.COUNT_COMMENT, B.CREATE_DATE, B.UPDATE_DATE, B.DEL_YN "
				+ " FROM USERS U, BOARDS B " + " WHERE U.USER_CODE = B.USER_CODE "
						+ " ORDER BY B.CREATE_DATE DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardListVO board = new BoardListVO();

				board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				board.setTITLE(rs.getString("TITLE"));
				board.setCOUNT_COMMENT(rs.getInt("COUNT_COMMENT"));
				board.setNAME(rs.getString("NAME"));
				board.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				board.setCOUNT_VIEW(rs.getInt("COUNT_VIEW"));

				boardList.add(board);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectBoards() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return boardList;
	}


	@Override
	public int getCount() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int res = 0;

		String sql = "SELECT COUNT(*) CNT FROM BOARDS";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				res = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - getCount() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return res;
	}
	
	@Override
	public int insertBoard(BoardsVO board) {

		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "INSERT INTO BOARDS " + "(BOARD_CODE, USER_CODE, TITLE, CONTEXT, COUNT_VIEW, DEL_YN, CREATE_DATE) "
				+ " VALUES(SEQ_BOARDS_BOARD_CODE.NEXTVAL, ?, ?, ?, 0, 'N', SYSDATE) ";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, board.getUSER_CODE());
			pstmt.setString(2, board.getTITLE());
			pstmt.setString(3, board.getCONTEXT());

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - insertBoard() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

	@Override
	public BoardsVO selectBoardContents(int BOARD_CODE) {
		Connection conn = getConnection();
		BoardsVO board = new BoardsVO();

		String sql = "SELECT * FROM BOARDS WHERE BOARD_CODE = " + BOARD_CODE;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				board.setUSER_CODE(rs.getInt("USER_CODE"));
				board.setTITLE(rs.getString("TITLE"));
				board.setCONTEXT(rs.getString("CONTEXT"));

				board.setCOUNT_VIEW(rs.getInt("COUNT_VIEW"));
				board.setCOUNT_COMMENT(rs.getInt("COUNT_COMMENT"));
				board.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				board.setUPDATE_DATE(rs.getDate("UPDATE_DATE"));
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectBoards() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return board;
	}

	@Override
	public void increaseCountView(int BOARD_CODE) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " UPDATE BOARDS "
				+ " SET COUNT_VIEW = COUNT_VIEW + 1 "
				+ " WHERE BOARD_CODE = ? ";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, BOARD_CODE);
			
			res = pstmt.executeUpdate();

			if (res > 0)
				commit(con);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. db 종료
			close(pstmt);
			close(con);
		}
		
	}


}
