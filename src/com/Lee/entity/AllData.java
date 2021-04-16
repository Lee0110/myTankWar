package com.Lee.entity;

import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

public class AllData {

	public static int gameLevel = 1;
	public static Health health = new Health(8, 8);// 第一颗红心的位置
	public static MyTank myTank = new MyTank(384, 544, 'U');// 我的坦克
	public static List<Emmo> myEmmoList = new LinkedList<Emmo>();
	public static int enemyNum = 0;// 敌人数量
	public static int enemyNumInc = 2;// 敌人数量增量
	public static int pressSpaceCount;// 按空格次数,第一次时就要启动线程
	public static List<EnemyTank> enemyTankList = new LinkedList<EnemyTank>();
	public static User user = new User();
	public static boolean isStart = false;// 游戏开始状态
	public static Score score = new Score();// 分数
	public static int saveScoreCount = 0;// 存分数的次数
	public static Timer timer;
}
