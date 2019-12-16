package com.DAO.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.FilesVO;
import com.VO.UsersVO;

public class FileDAOImpl extends JDBCTemplate implements FileDAO {

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

				if (rs.getString("DEL_YN").equals("Y"))
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

	@Override
	public int insertFile(FilesVO file) {
		
		Connection conn = getConnection();

		// BOARD_CODE 넣는 부분 확인해야함*****
		String sql = " INSERT INTO FILES VALUES (SEQ_FILES_FILE_CODE.NEXTVAL,"
				+ "(SELECT MAX(BOARD_CODE) FROM BOARDS), ?, ?, ?, ?, ?, SYSDATE, 'N') ";
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, file.getUSER_CODE());
			pstmt.setString(2, file.getFILE_ORIGINAL_NAME());
			pstmt.setString(3, file.getFILE_STORED_NAME());
			pstmt.setString(4, file.getFILE_PATH());
			pstmt.setString(5, file.getFILE_SIZE());

			res = pstmt.executeUpdate();
			
			if (res > 0)
				commit(conn);
			
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : FileDAOImpl - insertFile() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 어떤 게시글의 첨부파일 가져오기
	@Override
	public FilesVO selectFileContent(int FILE_CODE) {
		
		Connection conn = getConnection();

		String sql = " SELECT * FROM FILES WHERE FILE_CODE = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FilesVO file = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, FILE_CODE);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				file = new FilesVO();

				file.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				file.setUSER_CODE(rs.getInt("USER_CODE"));
				file.setFILE_ORIGINAL_NAME(rs.getString("FILE_ORIGINAL_NAME"));
				file.setFILE_STORED_NAME(rs.getString("FILE_STORED_NAME"));
				file.setFILE_PATH(rs.getString("FILE_PATH"));
				file.setFILE_SIZE(rs.getString("FILE_SIZE"));
				file.setCREATE_DATE(rs.getDate("CREATE_DATE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return file;
	}
}
