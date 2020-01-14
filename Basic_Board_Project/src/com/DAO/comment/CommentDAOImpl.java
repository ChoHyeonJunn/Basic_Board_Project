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

				comment.setGROUP_NO(rs.getInt("GROUP_NO"));
				comment.setGROUP_ORDER(rs.getInt("GROUP_ORDER"));
				comment.setGROUP_DEPTH(rs.getInt("GROUP_DEPTH"));

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

		String sql = " SELECT * FROM COMMENTS JOIN USERS USING(USER_CODE) WHERE BOARD_CODE = " + BOARD_CODE
				+ " ORDER BY GROUP_NO DESC, GROUP_ORDER ASC ";

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

				comment.setGROUP_NO(rs.getInt("GROUP_NO"));
				comment.setGROUP_ORDER(rs.getInt("GROUP_ORDER"));
				comment.setGROUP_DEPTH(rs.getInt("GROUP_DEPTH"));

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

	// 원 댓글 작성
	@Override
	public int insertComment(CommentsVO comment) {

		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " INSERT INTO COMMENTS(COMMENT_CODE, BOARD_CODE, USER_CODE, GROUP_NO, CONTEXT) "
				+ " VALUES(SEQ_COMMENTS_COMMENT_CODE.NEXTVAL, ?, ?, SEQ_COMMENTS_COMMENT_CODE.CURRVAL, ?)";

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

	// 원 댓글의 대댓글 작성
	@Override
	public int insertComment(CommentsVO parentComment, CommentsVO subComment) {

		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = " INSERT INTO COMMENTS(COMMENT_CODE, BOARD_CODE, USER_CODE, GROUP_NO, GROUP_ORDER, GROUP_DEPTH, CONTEXT) "
				+ " VALUES(SEQ_COMMENTS_COMMENT_CODE.NEXTVAL, ?, ?, ?, ?, ?, ?) ";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, parentComment.getBOARD_CODE());
			pstmt.setInt(2, subComment.getUSER_CODE());
			pstmt.setInt(3, parentComment.getGROUP_NO());
			pstmt.setInt(4, parentComment.getGROUP_ORDER() + 1);
			pstmt.setInt(5, parentComment.getGROUP_DEPTH() + 1);
			pstmt.setString(6, subComment.getCONTEXT());

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

		String sql = " UPDATE COMMENTS SET CONTEXT = ?, UPDATE_DATE = SYSDATE " + " WHERE COMMENT_CODE = ? ";
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
	public int maxDepth(int GROUP_NO) {

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int res = 0;

		String sql = " SELECT MAX(GROUP_DEPTH) FROM COMMENTS WHERE GROUP_NO = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, GROUP_NO);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				res = rs.getInt("MAX(GROUP_DEPTH)");
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - maxDepth(int GROUP_NO) SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
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

		String sql = " UPDATE COMMENTS SET COUNT_GOOD = COUNT_GOOD+1 " + " WHERE COMMENT_CODE = ? ";
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

		String sql = " UPDATE COMMENTS SET COUNT_BAD = COUNT_BAD+1 " + " WHERE COMMENT_CODE = ? ";
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
