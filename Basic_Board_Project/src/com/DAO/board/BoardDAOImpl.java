package com.DAO.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.BoardsVO;
import com.VO.CommentsVO;
import com.VO.FilesVO;

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

	@Override
	public ArrayList<FilesVO> selectFiles() throws Exception {

		Connection conn = getConnection();
		ArrayList<FilesVO> filesList = new ArrayList<FilesVO>();

		String sql = "SELECT * FROM FILES";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FilesVO file = new FilesVO();

				file.setFILE_CODE(rs.getInt("FILE_CODE"));
				file.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				file.setUSER_CODE(rs.getInt("USER_CODE"));
				file.setFILE_ORIGINAL_NAME(rs.getString("FILE_ORIGINAL_NAME"));

				file.setFILE_STORED_NAME(rs.getString("FILE_STORED_NAME"));
				file.setFILE_PATH(rs.getString("FILE_PATH"));
				file.setFILE_SIZE(rs.getString("FILE_SIZE"));
				file.setCREATE_DATE(rs.getDate("CREATE_DATE"));

				if(rs.getString("DEL_YN").equals("Y"))
					file.setDEL_YN(true);
				else
					file.setDEL_YN(false);

				filesList.add(file);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : BoardDAOImpl - selectFiles() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return filesList;
	}

}
