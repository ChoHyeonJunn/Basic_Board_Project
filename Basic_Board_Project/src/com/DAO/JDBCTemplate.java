package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// db 연결, 계정 연결
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver 연결 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("[ ERROR ] : Driver 연결을 확인하세요.");
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "board";
		String pw = "board";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pw);
			conn.setAutoCommit(false);
			System.out.println("계정 연결 성공!\n");
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : 계정 연결을 확인하세요.");
			e.printStackTrace();
		}
		return conn;
	}
	
	// db 종료
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// commit & rollback
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
