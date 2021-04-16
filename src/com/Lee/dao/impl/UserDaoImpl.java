package com.Lee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Lee.dao.UserDao;
import com.Lee.entity.User;

public class UserDaoImpl implements UserDao {

	/**
	 * @Title: login
	 * @Desc:登录
	 * @param con
	 * @param user
	 * @return
	 * @throws SQLException
	 * @see com.Lee.dao.impl.UserDao#login(java.sql.Connection, com.Lee.entity.User)
	 */
	@Override
	public User login(Connection con, User user) throws SQLException {
		User resultUser = null;
		String sql = "select * from user where username=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getLong("id"));
			resultUser.setUsername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setMoney(rs.getLong("money"));
		}
		return resultUser;
	}

	/**
	 * 
	 * @Title: add
	 * @Desc: 注册
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public int register(Connection con, User user) throws Exception {
		String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		return pstmt.executeUpdate();
	}

	/**
	 * 
	 * @Title: isRegister
	 * @Desc: 判断该用户名是否被注册
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isRegister(Connection con, User user) throws Exception {
		String sql = "select * from user where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: moneyUpdate
	 * @Desc:充值金币
	 * @param con
	 * @param user
	 * @param money
	 * @return
	 * @throws Exception
	 */
	@Override
	public int moneyUpdate(Connection con, User user) throws Exception {
		String sql = "update user set money=money+500 where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		return pstmt.executeUpdate();

	}

	/**
	 * 
	 * @Title: getMoney
	 * @Desc: 查询金币
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long getMoney(Connection con, User user) throws Exception {
		String sql = "select money where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong("money");
		}
		return null;
	}

	/**
	 * 
	 * @Title: spendMoney
	 * @Desc: 消费金币
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public int spendMoney(Connection con, User user) throws Exception {
		String sql = "update user set money=money-500 where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		return pstmt.executeUpdate();

	}
}
