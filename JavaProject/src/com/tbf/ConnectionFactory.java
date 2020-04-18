package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Sunny Liu, Bryce Yong 
 * Date: 03/04/2020 
 * This is a connection factory with methods to connect to the jdbc driver
 * and close the connection 
 * 
 */

public class ConnectionFactory {
	//database info
	public static final String url = "jdbc:mysql://cse.unl.edu/byong?useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String username = "byong";
	public static final String password = "iK3anPF4";
	
	//method to load jdbc driver
	public static void loadDriver() {
		try {
			String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	//method to make connection
	public static Connection createConnection() {	
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//method to close connection for prepared statement
	public static void closeConnection(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	//method to close connection for result set
	public static void closeConnection(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	//method to close connection 
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





