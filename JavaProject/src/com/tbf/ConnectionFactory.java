package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static final String url = "jdbc:mysql://cse.unl.edu/byong?useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String username = "byong";
	public static final String password = "iK3anPF4";
	
	public static void loadDriver() {
		try {
			String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public static Connection createConnection() {	
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public static void closeConnection(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}


