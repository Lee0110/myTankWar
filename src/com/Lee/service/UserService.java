package com.Lee.service;

import java.sql.Connection;

import com.Lee.entity.User;

public interface UserService {

	/**
	 * 
	 * @Title: login
	 * @Desc: 登录 MD5加密
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User login(Connection con, User user) throws Exception;

	/**
	 * 
	 * @Title: register
	 * @Desc: 注册 MD5加密
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean register(Connection con, User user) throws Exception;

	/**
	 * 
	 * @Title: moneyUpdate
	 * @Desc: 充值金币
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
	 * @return -2生命值已经大于10，-1金币不足请充值，0充值失败，1充值成功
	 * @throws Exception
	 */
	int spendMoney(Connection con, User user) throws Exception;

}