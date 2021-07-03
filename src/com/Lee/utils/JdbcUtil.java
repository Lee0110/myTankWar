package com.Lee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	static final String IP_ADDR = "请输入...";// 输入你的mysqlIP地址,本地则输入localhost
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE = "tankwar";
	static final String DB_URL = "jdbc:mysql://" + IP_ADDR + ":3306/" + DATABASE
			+ "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "请输入...";
	static final String PASS = "请输入...";

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
