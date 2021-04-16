package com.Lee.service.impl;

import java.sql.Connection;

import com.Lee.dao.UserDao;
import com.Lee.dao.impl.UserDaoImpl;
import com.Lee.entity.AllData;
import com.Lee.entity.User;
import com.Lee.service.UserService;
import com.Lee.utils.MD5Tool;

public class UserServiceImpl implements UserService {
	private UserDao ud = new UserDaoImpl();

	/**
	 * @Title: login
	 * @Desc:
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 * @see com.Lee.service.impl.UserService#login(java.sql.Connection,
	 *      com.Lee.entity.User)
	 */
	@Override
	public User login(Connection con, User user) throws Exception {
		user.setPassword(MD5Tool.encryByMD5(user.getPassword()));// MD5加密
		return ud.login(con, user);
	}

	/**
	 * 
	 * @Title: register
	 * @Desc: 注册 MD5加密
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean register(Connection con, User user) throws Exception {
		user.setPassword(MD5Tool.encryByMD5(user.getPassword()));// MD5加密
		if (ud.isRegister(con, user)) {// 判断是否被注册过
			return false;
		}
		int flag = ud.register(con, user);
		if (flag == 1) {
			return true;
		}
		return false;

	}

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
	@Override
	public int moneyUpdate(Connection con, User user) throws Exception {
		return ud.moneyUpdate(con, user);
	}

	/**
	 * 
	 * @Title: spendMoney
	 * @Desc: 消费金币
	 * @param con
	 * @param user
	 * @return -2生命值已经大于10，-1金币不足请充值，0充值失败，1充值成功
	 * @throws Exception
	 */
	@Override
	public int spendMoney(Connection con, User user) throws Exception {
		if (user.getMoney() <= 0) {
			return -1;
		} else if (AllData.myTank.getHealthNum() >= 10) {
			return -2;
		} else {
			return ud.spendMoney(con, user);
		}
	}
}
