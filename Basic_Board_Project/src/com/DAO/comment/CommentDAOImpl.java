package com.DAO.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.CommentsVO;

public class CommentDAOImpl extends JDBCTemplate implements CommentDAO {

	@Override
	public ArrayList<CommentsVO> selectComments() throws Exception {
		ArrayList<CommentsVO> commentsList = new ArrayList<CommentsVO>();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM COMMENTS";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentsVO comment = new CommentsVO();

				comment.setCOMMENT_CODE(rs.getInt("COMMENT_CODE"));
				comment.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				comment.setUSER_CODE(rs.getInt("USER_CODE"));
				comment.setCONTEXT(rs.getString("CONTEXT"));

				comment.setCOUNT_GOOD(rs.getInt("COUNT_GOOD"));
				comment.setCOUNT_BAD(rs.getInt("COUNT_BAD"));
				comment.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				comment.setUPDATE_DATE(rs.getDate("UPDATE_DATE"));

				commentsList.add(comment);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectComments() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return commentsList;
	}

	@Override
	public ArrayList<CommentsVO> selectComments(int BOARD_CODE) {
		ArrayList<CommentsVO> commentsList = new ArrayList<CommentsVO>();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT * FROM COMMENTS JOIN USERS USING(USER_CODE) WHERE BOARD_CODE = " + BOARD_CODE;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentsVO comment = new CommentsVO();

				comment.setCOMMENT_CODE(rs.getInt("COMMENT_CODE"));
				comment.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				comment.setUSER_CODE(rs.getInt("USER_CODE"));
				comment.setCONTEXT(rs.getString("CONTEXT"));

				comment.setCOUNT_GOOD(rs.getInt("COUNT_GOOD"));
				comment.setCOUNT_BAD(rs.getInt("COUNT_BAD"));
				comment.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				comment.setUPDATE_DATE(rs.getDate("UPDATE_DATE"));

				comment.setNAME(rs.getString("NAME"));
				commentsList.add(comment);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectComments(BOARD_CODE) SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return commentsList;
	}

	@Override
	public int insertComment(CommentsVO comment) {

		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " INSERT INTO COMMENTS(COMMENT_CODE, BOARD_CODE, USER_CODE, CONTEXT) " + 
				" VALUES(SEQ_COMMENTS_COMMENT_CODE.NEXTVAL, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, comment.getBOARD_CODE());
			pstmt.setInt(2, comment.getUSER_CODE());
			pstmt.setString(3, comment.getCONTEXT());

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : CommentDAOImpl - insertComment() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

	@Override
	public int updateComment(CommentsVO comment) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " UPDATE COMMENTS SET CONTEXT = ?, UPDATE_DATE = SYSDATE " + 
				" WHERE COMMENT_CODE = ? ";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, comment.getCONTEXT());
			pstmt.setInt(2, comment.getCOMMENT_CODE());

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : CommentDAOImpl - updateCommnet() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

	@Override
	public int deleteComment(int COMMENT_CODE) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " DELETE FROM COMMENTS WHERE COMMENT_CODE = ? ";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, COMMENT_CODE);

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : CommentDAOImpl - deleteComment() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

	@Override
	public int goodComment(int COMMENT_CODE) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " UPDATE COMMENTS SET COUNT_GOOD = COUNT_GOOD+1 " + 
				" WHERE COMMENT_CODE = ? ";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, COMMENT_CODE);

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : CommentDAOImpl - goodCommnet() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

	@Override
	public int badComment(int COMMENT_CODE) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " UPDATE COMMENTS SET COUNT_BAD = COUNT_BAD+1 " + 
				" WHERE COMMENT_CODE = ? ";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, COMMENT_CODE);

			res = pstmt.executeUpdate();
			if (res > 0)
				commit(con);
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : CommentDAOImpl - badCommnet() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}

		return res;
	}

}
