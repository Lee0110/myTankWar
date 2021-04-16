package com.Lee.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.Lee.entity.User;

public interface UserDao {

	/**
	 * 
	 * @Title: login
	 * @Desc: 登录
	 * @param con
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	User login(Connection con, User user) throws SQLException;

	/**
	 * 
	 * @Title: add
	 * @Desc: 注册
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int register(Connection con, User user) throws Exception;

	/**
	 * 
	 * @Title: isRegister
	 * @Desc: 判断该用户名是否被注册
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean isRegister(Connection con, User user) throws Exception;

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
	int moneyUpdate(Connection con, User user) throws Exception;

	/**
	 * 
	 * @Title: spendMoney
	 * @Desc: 消费金币
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int spendMoney(Connection con, User user) throws Exception;

	/**
	 * 
	 * @Title: getMoney
	 * @Desc: 查询金币
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Long getMoney(Connection con, User user) throws Exception;
}