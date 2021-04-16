package com.Lee.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Lee.entity.AllData;
import com.Lee.entity.Emmo;
import com.Lee.entity.EnemyTank;
import com.Lee.entity.Score;
import com.Lee.entity.User;
import com.Lee.service.ScoreService;
import com.Lee.service.UserService;
import com.Lee.service.impl.ScoreServiceImpl;
import com.Lee.service.impl.UserServiceImpl;
import com.Lee.utils.JdbcUtil;
import com.Lee.view.HelpFrm;
import com.Lee.view.RankFrm;
import com.Lee.view.RechargeFrm;
import com.Lee.view.RegisterFrm;
import com.Lee.view.ShopFrm;
import com.Lee.view.StartGame;

public class Controller {

	/**
	 * 
	 * @Title: loginActionPerformed
	 * @Desc: 登陆界面登录事件处理
	 * @param evt
	 * @param usernameTxt
	 * @param passwordTxt
	 */
	public static boolean loginActionPerformed(ActionEvent evt, JTextField usernameTxt, JPasswordField passwordTxt) {
		UserService us = new UserServiceImpl();
		String username = usernameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		String pattern = "[0-9a-zA-Z]{3,12}";
		if (!username.matches(pattern) || !password.matches(pattern)) {
			JOptionPane.showMessageDialog(null, "用户名或密码不满足要求，请重新输入！");
			return false;
		}
		User user = new User(username, password);

		try {
			Connection con = JdbcUtil.getCon();
			User currentUser = us.login(con, user);
			if (currentUser != null) {
				AllData.user = currentUser;
				AllData.user.setMoney(currentUser.getMoney());
				AllData.score = new Score(0L, currentUser.getId());
				new StartGame().setVisible(true);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "登录失败");
				passwordTxt.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败");
		}
		return false;
	}

	/**
	 * 
	 * @Title: registerActionPerformed
	 * @Desc: 登陆界面注册事件处理
	 * @param evt
	 */
	public static void loginFrmRegisterActionPerformed(ActionEvent evt) {
		new RegisterFrm().setVisible(true);
	}

	/**
	 * 
	 * @Title: RegisterActionPerformed
	 * @Desc: 注册界面注册事件处理
	 * @param evt
	 */
	public static boolean RegisterActionPerformed(ActionEvent evt, JTextField usernameTxt, JPasswordField passwordTxt) {
		UserService us = new UserServiceImpl();
		String username = usernameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		String pattern = "[0-9a-zA-Z]{3,12}";
		if (!username.matches(pattern) || !password.matches(pattern)) {
			JOptionPane.showMessageDialog(null, "用户名或密码不满足要求，请重新输入！");
			passwordTxt.setText("");
			usernameTxt.setText("");
			return false;
		}
		User user = new User(username, password);

		try {
			Connection con = JdbcUtil.getCon();
			boolean flag = us.register(con, user);
			if (flag) {
				JOptionPane.showMessageDialog(null, "注册成功");
				return true;
			} else {
				passwordTxt.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败");
		}
		return false;
	}

	/**
	 * 
	 * @Title: menuClick
	 * @Desc: 点击菜单游戏暂停
	 * @param e
	 */
	public static void menuClick(MouseEvent e) {
		AllData.isStart = false;

	}

	/**
	 * 
	 * @Title: rechargeMouseClicked
	 * @Desc: 点击菜单充值事件处理
	 * @param e
	 */
	public static void rechargeMouseClicked(MouseEvent e) {
		AllData.isStart = false;
		new RechargeFrm().setVisible(true);

	}

	/**
	 * 
	 * @Title: helpMouseClicked
	 * @Desc: 点击菜单帮助事件处理
	 * @param e
	 */
	public static void helpMouseClicked(MouseEvent e) {
		AllData.isStart = false;
		new HelpFrm().setVisible(true);
	}

	/**
	 * 
	 * @Title: rankMouseClicked
	 * @Desc: 点击菜单排行榜事件处理
	 * @param e
	 */
	public static void rankMouseClicked(MouseEvent e) {
		AllData.isStart = false;
		new RankFrm().setVisible(true);

	}

	/**
	 * 
	 * @Title: timerActionPerformed
	 * @Desc: timer事件处理
	 */
	public static void timerActionPerformed() {
		myEmmoAndEnemyTankCollisionDetecyion();
		myTankAndEnemyTankCollisionDetecyion();
		myTankAndEnemyTankEmmoCollisionDetecyion();
		enemyTankAndEnemyTankEmmoCollisionDetecyion();
		AllData.timer.start();

	}

	/**
	 * 
	 * @Title: myEmmoAndEnemyTankCollisionDetecyion
	 * @Desc: 我的子弹和敌人坦克碰撞检测
	 */
	private static void myEmmoAndEnemyTankCollisionDetecyion() {
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			for (Emmo emmo : AllData.myEmmoList) {
				boolean flag = new Rectangle(emmo.getX(), emmo.getY(), 16, 16)
						.intersects(new Rectangle(enemyTank.getX(), enemyTank.getY(), 32, 32));
				if (flag) {
					enemyTank.setX(-32);
					enemyTank.setY(-32);
					enemyTank.setDie(true);
					emmo.setX(-50);
					emmo.setY(-50);
					emmo.setDie(true);
					AllData.score.setGrade(AllData.score.getGrade() + 200);
					emmo = null;
				}
			}

		}
	}

	/**
	 * 
	 * @Title: myTankAndEnemyTankCollisionDetecyion
	 * @Desc: 我的坦克和敌人坦克碰撞检测
	 */
	private static void myTankAndEnemyTankCollisionDetecyion() {
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			boolean flag = new Rectangle(AllData.myTank.getX(), AllData.myTank.getY(), 32, 32)
					.intersects(new Rectangle(enemyTank.getX(), enemyTank.getY(), 32, 32));
			if (flag) {
				enemyTank.setX(-32);
				enemyTank.setY(-32);
				enemyTank.setDie(true);
				AllData.myTank.setHealthNum(AllData.myTank.getHealthNum() - 1);
				AllData.myTank.setX(384);
				AllData.myTank.setY(544);
				AllData.myTank.setStatus('U');
				AllData.score.setGrade(AllData.score.getGrade() + 100);
			}
		}
	}

	/**
	 * 
	 * @Title: myTankAndEnemyTankEmmoCollisionDetecyion
	 * @Desc: 我的坦克和敌人子弹碰撞检测
	 */
	private static void myTankAndEnemyTankEmmoCollisionDetecyion() {
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			for (Emmo emmo : enemyTank.getEnemyTankEmmoList()) {
				boolean flag = new Rectangle(AllData.myTank.getX(), AllData.myTank.getY(), 32, 32)
						.intersects(new Rectangle(emmo.getX(), emmo.getY(), 16, 16));
				if (flag) {
					AllData.myTank.setHealthNum(AllData.myTank.getHealthNum() - 1);
					AllData.myTank.setX(384);
					AllData.myTank.setY(544);
					AllData.myTank.setStatus('U');
					emmo.setX(-50);
					emmo.setY(-50);
					emmo.setDie(true);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: enemyTankAndEnemyTankEmmoCollisionDetecyion
	 * @Desc: 敌人坦克和敌人子弹碰撞检测
	 */
	private static void enemyTankAndEnemyTankEmmoCollisionDetecyion() {
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			for (Emmo emmo : enemyTank.getEnemyTankEmmoList()) {
				for (EnemyTank anotherEnemyTank : AllData.enemyTankList) {
					boolean flag = new Rectangle(anotherEnemyTank.getX(), anotherEnemyTank.getY(), 32, 32)
							.intersects(new Rectangle(emmo.getX(), emmo.getY(), 16, 16));
					if (flag) {
						anotherEnemyTank.setX(-32);
						anotherEnemyTank.setY(-32);
						anotherEnemyTank.setDie(true);
						emmo.setX(-50);
						emmo.setY(-50);
						emmo.setDie(true);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: keyPressedListener
	 * @Desc: 键盘监听
	 * @param e
	 */
	public static void keyPressedListener(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && AllData.myTank.getHealthNum() != 0) {
			AllData.isStart = !AllData.isStart;// 按空格键开始游戏
			AllData.timer.start();
			if (AllData.pressSpaceCount == 0) {
				AllData.pressSpaceCount++;
				for (EnemyTank enemyTank : AllData.enemyTankList) {
					enemyTank.start();
				}
			}
		}
		if (AllData.isStart && AllData.myTank.getHealthNum() != 0) {
			if (e.getKeyCode() == KeyEvent.VK_J) {// 发射子弹判断
				Emmo myEmmo = new Emmo(AllData.myTank);
				myEmmo.start();
				AllData.myEmmoList.add(myEmmo);
			}
			switch (e.getKeyCode()) {// wasd上下左右移动
			case KeyEvent.VK_W:
				if (AllData.myTank.getY() > 0) {
					AllData.myTank.setY(AllData.myTank.getY() - 16);
				}
				AllData.myTank.setStatus('U');
				AllData.myTank.setMyTankIcon();
				// System.out.println("x:" + AllData.myTank.getX() + " y:" +
				// AllData.myTank.getY());
				break;
			case KeyEvent.VK_S:
				if (AllData.myTank.getY() < 624) {
					AllData.myTank.setY(AllData.myTank.getY() + 16);
				}
				AllData.myTank.setStatus('D');
				AllData.myTank.setMyTankIcon();
				// System.out.println("x:" + AllData.myTank.getX() + " y:" +
				// AllData.myTank.getY());
				break;
			case KeyEvent.VK_A:
				if (AllData.myTank.getX() > 0) {
					AllData.myTank.setX(AllData.myTank.getX() - 16);
				}
				AllData.myTank.setStatus('L');
				AllData.myTank.setMyTankIcon();
				// System.out.println("x:" + AllData.myTank.getX() + " y:" +
				// AllData.myTank.getY());
				break;
			case KeyEvent.VK_D:
				if (AllData.myTank.getX() < 752) {
					AllData.myTank.setX(AllData.myTank.getX() + 16);
				}
				AllData.myTank.setStatus('R');
				AllData.myTank.setMyTankIcon();
				// System.out.println("x:" + AllData.myTank.getX() + " y:" +
				// AllData.myTank.getY());
				break;
			}
		}

	}

	/**
	 * 
	 * @Title: dataInit
	 * @Desc: 数据初始化
	 */
	public static void dataInit() {

		AllData.pressSpaceCount = 0;
		AllData.myTank.setX(384); // 初始化我的坦克的位置
		AllData.myTank.setY(544);
		AllData.myTank.setStatus('U');
		AllData.enemyNum = 3 + AllData.enemyNumInc;// 敌人坦克数量
		AllData.enemyTankList.clear();// 清空敌人链表
		if (AllData.gameLevel <= 2) {
			for (int i = 0; i < AllData.enemyNum; i++) {// 初始化敌人的位置
				AllData.enemyTankList.add(new EnemyTank(64 + 64 * i, 64, 'D'));
			}
		}
		AllData.myEmmoList.clear();// 清空我的子弹链表
	}

	/**
	 * 
	 * @Title: purchaseHealthActionPerformed
	 * @Desc: 商城界面购买生命值事件处理
	 * @param e
	 * @return -2生命值已经大于10，-1金币不足请充值，0充值失败，1充值成功
	 */
	public static int purchaseHealthActionPerformed(ActionEvent e) {
		try {
			UserService us = new UserServiceImpl();
			Connection con = JdbcUtil.getCon();
			return us.spendMoney(con, AllData.user);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;

	}

	/**
	 * 
	 * @Title: shopMouseClicked
	 * @Desc: 主界面商城点击事件
	 * @param e
	 */
	public static void shopMouseClicked(MouseEvent e) {
		AllData.isStart = false;
		new ShopFrm().setVisible(true);

	}

	/**
	 * 
	 * @Title: allEnemyTankDie
	 * @Desc: 敌人是否全部灭亡
	 * @return
	 */
	public static boolean allEnemyTankDie() {
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			if (!enemyTank.isDie()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: saveScore
	 * @Desc: 游戏胜利，存储分数
	 */
	public static void saveScore() {
		try {
			ScoreService ss = new ScoreServiceImpl();
			Connection con = JdbcUtil.getCon();
			ss.saveGrade(con, AllData.score);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
