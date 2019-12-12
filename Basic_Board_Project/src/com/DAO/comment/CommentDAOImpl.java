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

		Connection conn = getConnection();
		ArrayList<CommentsVO> commentsList = new ArrayList<CommentsVO>();

		String sql = "SELECT * FROM COMMENTS";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

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

}
