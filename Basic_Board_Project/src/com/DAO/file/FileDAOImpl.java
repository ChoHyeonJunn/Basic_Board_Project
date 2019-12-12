package com.DAO.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DAO.JDBCTemplate;
import com.VO.FilesVO;

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
}
