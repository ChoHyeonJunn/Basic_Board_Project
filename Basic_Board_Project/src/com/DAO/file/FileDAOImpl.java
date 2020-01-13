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

	// 새로운 글 생성과 동시에 파일 추가
	@Override
	public int insertFile(FilesVO file) {
		
		Connection conn = getConnection();

		// BOARD_CODE 넣는 부분 확인해야함*****
		String sql = " INSERT INTO FILES VALUES (SEQ_FILES_FILE_CODE.NEXTVAL,"
				+ "(SELECT MAX(BOARD_CODE) FROM BOARDS), ?, ?, ?, ?, ?, SYSDATE) ";
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
	public FilesVO selectFileContents(int BOARD_CODE) {
		
		Connection conn = getConnection();

		String sql = " SELECT * "
				+ " FROM FILES JOIN BOARDS USING (BOARD_CODE) "
				+ " WHERE BOARD_CODE = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FilesVO file = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BOARD_CODE);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				file = new FilesVO();

				file.setFILE_CODE(rs.getInt("FILE_CODE"));
				file.setUSER_CODE(rs.getInt("USER_CODE"));
				file.setFILE_ORIGINAL_NAME(rs.getString("FILE_ORIGINAL_NAME"));
				file.setFILE_STORED_NAME(rs.getString("FILE_STORED_NAME"));
				file.setFILE_PATH(rs.getString("FILE_PATH"));
				file.setFILE_SIZE(rs.getString("FILE_SIZE"));
				file.setCREATE_DATE(rs.getDate("CREATE_DATE"));
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : FileDAOImpl - selectFileContents() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return file;
	}

	// FILE_CODE로 첨부파일 내용 가져오기
	@Override
	public FilesVO selectOneFile(int FILE_CODE) {
		
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
			System.out.println("[ ERROR ] : FileDAOImpl - selectOneFile() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return file;
	}

	
	// 어떤 게시글의 첨부파일 삭제
	@Override
	public int deleteFile(int FILE_CODE) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE FILES SET FILE_ORIGINAL_NAME = NULL, FILE_STORED_NAME = NULL, FILE_PATH = NULL, FILE_SIZE = NULL WHERE FILE_CODE = " + FILE_CODE;
		
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : FileDAOImpl - deleteFile() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 어떤 게시글의 첨부파일 수정
	@Override
	public int updateFile(FilesVO vo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE FILES SET FILE_ORIGINAL_NAME = ?, FILE_STORED_NAME = ?, FILE_PATH = ?, FILE_SIZE = ? WHERE FILE_CODE = ? ";
		
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFILE_ORIGINAL_NAME());
			pstmt.setString(2, vo.getFILE_STORED_NAME());
			pstmt.setString(3, vo.getFILE_PATH());
			pstmt.setString(4, vo.getFILE_SIZE());
			pstmt.setInt(5, vo.getFILE_CODE());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : FileDAOImpl - updateFile() SQL 확인하세요.");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}
}
