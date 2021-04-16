package com.Lee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE = "tankwar";
	static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE
			+ "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "123";

	public static Connection getCon() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	public void closeCon(Connection con) throws SQLException {
		if (con != null)
			con.close();
	}
}