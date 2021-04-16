package com.Lee.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.Lee.controller.Controller;
import com.Lee.entity.AllData;
import com.Lee.entity.Emmo;
import com.Lee.entity.EnemyTank;

public class GamePanel extends JPanel implements ActionListener {

	/**
	 * 
	 * @Title: GamePanel
	 * @Desc: 构造器
	 */
	public GamePanel() {
		init();
		this.setFocusable(true);// 聚焦，这样键盘监听才有效果
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {// 监听键盘按压
				super.keyPressed(e);
				Controller.keyPressedListener(e);
				if ((e.getKeyCode() == KeyEvent.VK_R && AllData.myTank.getHealthNum() == 0)
						|| (e.getKeyCode() == KeyEvent.VK_R && AllData.gameLevel > 2)) {// 我的坦克死亡或游戏胜利，按R重开
					AllData.enemyNumInc = 2;
					AllData.myTank.setHealthNum(3);
					AllData.saveScoreCount = 0;
					AllData.score.setGrade(0L);
					AllData.isStart = false;
					AllData.gameLevel = 0;
					init();
				}
				repaint();
			}

		});
	}

	/**
	 * 
	 * @Title: init
	 * @Desc: 初始化，游戏开始或游戏重新开始时调用的方法
	 */
	private void init() {

		Controller.dataInit();// 数据初始化
		AllData.timer = new Timer(42, this);
		AllData.timer.start();
	}

	/**
	 * 
	 * @Title: paintComponent
	 * @Desc: 画笔，画所有游戏里的元素
	 * @param g
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.lightGray);

		// 画我的坦克
		AllData.myTank.getMyTankIcon().paintIcon(this, g, AllData.myTank.getX(), AllData.myTank.getY());

		// 画生命值
		for (int i = 0; i < AllData.myTank.getHealthNum(); i++) {
			AllData.health.getHealthIcon().paintIcon(this, g, AllData.health.getX() + 32 * i, AllData.health.getY());
		}

		// 画敌人坦克
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			if (!enemyTank.isDie() && AllData.gameLevel <= 2) {// 坦克没死才画
				enemyTank.getEnemyTankIcon().paintIcon(this, g, enemyTank.getX(), enemyTank.getY());
			}
		}

		// 画我的子弹
		for (Emmo emmo : AllData.myEmmoList) {
			if (!emmo.isDie()) {
				emmo.getEmmoIcon().paintIcon(this, g, emmo.getX(), emmo.getY());
			}
		}

		// 画敌人子弹
		for (EnemyTank enemyTank : AllData.enemyTankList) {
			for (Emmo emmo : enemyTank.getEnemyTankEmmoList()) {
				emmo.getEmmoIcon().paintIcon(this, g, emmo.getX(), emmo.getY());
			}
		}

		// 游戏未开始，请求玩家按下空格开始
		if (!AllData.isStart && AllData.gameLevel <= 2) {
			g.setColor(Color.darkGray);
			g.setFont(new Font("宋体", Font.BOLD, 40));
			g.drawString("按下空格游戏开始", 250, 310);
		}

		// 胜利
		if (AllData.gameLevel > 2) {
			g.setColor(Color.darkGray);
			g.setFont(new Font("宋体", Font.PLAIN, 40));
			g.drawString("胜利!!!按R重开", 280, 310);
		}

		// 我的坦克死亡
		if (AllData.myTank.getHealthNum() == 0) {
			g.setColor(Color.darkGray);
			g.setFont(new Font("宋体", Font.PLAIN, 40));
			g.drawString("失败!!!按R重开", 280, 310);
		}

		// 画我的分数、关卡
		g.setColor(Color.darkGray);
		g.setFont(new Font("宋体", Font.PLAIN, 10));
		g.drawString("欢迎您," + AllData.user.getUsername(), 700, 20);
		g.drawString("分数：" + AllData.score.getGrade(), 700, 36);
		g.drawString("第 " + AllData.gameLevel + " 关", 700, 52);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (AllData.isStart && AllData.gameLevel <= 2 && AllData.myTank.getHealthNum() != 0) {
			if (Controller.allEnemyTankDie()) {// 敌人全死，开启下一关
				AllData.gameLevel += 1;
				AllData.enemyNumInc += 2;
				AllData.isStart = false;
				AllData.pressSpaceCount = 0;
				init();
			}
			Controller.timerActionPerformed();
			repaint();
		}
		if ((AllData.gameLevel > 2 && AllData.saveScoreCount == 0)
				|| (AllData.myTank.getHealthNum() == 0 && AllData.saveScoreCount == 0)) {// 游戏胜利或我的坦克死亡，存储分数
			Controller.saveScore();
			AllData.enemyTankList.clear();
			AllData.saveScoreCount += 1;
		}

	}

}
