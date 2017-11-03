package com.gradience.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

public class DBConnection {
	private static DBConnection _instance;
	private static String _URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";

	public Connection conn;

	private DBConnection() {
		String user = new String(Base64.getDecoder().decode("YWNoYXVoYTM="));
		String password = new String(Base64.getDecoder().decode("MjAwMjA4MjE4"));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			this.conn = DriverManager.getConnection(DBConnection._URL, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBConnection instance() {
		if (_instance == null) {
			_instance = new DBConnection();
		}
		return _instance;
	}
}
