package com.Lee.entity;

import javax.swing.ImageIcon;

public class MyTank {

	private int x;// x坐标
	private int y;// y坐标
	private char status = 'U';// 头的朝向UDLF对应上下左右
	private int healthNum = 3;// 生命值，默认为3
	private ImageIcon myTankIcon;

	public MyTank() {
		super();
	}

	public MyTank(int x, int y, char status) {
		super();
		this.x = x;
		this.y = y;
		this.status = status;
		this.setMyTankIcon();
	}

	public ImageIcon getMyTankIcon() {
		return myTankIcon;
	}

	public void setMyTankIcon() {
		switch (this.status) {
		case 'U':
			this.myTankIcon = new ImageIcon(MyTank.class.getResource("/images/myTankUp.png"));
			break;
		case 'D':
			this.myTankIcon = new ImageIcon(MyTank.class.getResource("/images/myTankDown.png"));
			break;
		case 'L':
			this.myTankIcon = new ImageIcon(MyTank.class.getResource("/images/myTankLeft.png"));
			break;
		case 'R':
			this.myTankIcon = new ImageIcon(MyTank.class.getResource("/images/myTankRight.png"));
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getHealthNum() {
		return healthNum;
	}

	public void setHealthNum(int healthNum) {
		this.healthNum = healthNum;
	}

}
