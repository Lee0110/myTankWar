package com.Lee.entity;

import javax.swing.ImageIcon;

public class Emmo extends Thread {

	private int x;// x坐标
	private int y;// y坐标
	private char status = 'U';// 头的朝向UDLR对应上下左右
	private boolean isDie = false;// 子弹是否死亡
	private ImageIcon emmoIcon;

	@Override
	public void run() {
		try {
			while (!isDie) {
				if ((x >= 0 && x <= 768 && y >= 0 && y <= 640)) {
					switch (this.status) {
					case 'U':
						this.setY(this.getY() - 16);
						break;
					case 'D':
						this.setY(this.getY() + 16);
						break;
					case 'L':
						this.setX(this.getX() - 16);
						break;
					case 'R':
						this.setX(this.getX() + 16);
					}
				} else {
					isDie = true;
				}
				Thread.sleep(42);
				Thread.yield();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Emmo(EnemyTank enemyTank) {
		super();
		this.status = enemyTank.getStatus();
		switch (this.status) {
		case 'U':
			setX(enemyTank.getX() + 8);
			setY(enemyTank.getY() - 16);
			break;
		case 'D':
			setX(enemyTank.getX() + 8);
			setY(enemyTank.getY() + 32);
			break;
		case 'L':
			setX(enemyTank.getX() - 16);
			setY(enemyTank.getY() + 8);
			break;
		case 'R':
			setX(enemyTank.getX() + 32);
			setY(enemyTank.getY() + 8);
		}

		setEmmoIcon();
	}

	public Emmo(MyTank myTank) {
		super();
		this.status = myTank.getStatus();
		switch (this.status) {
		case 'U':
			setX(myTank.getX() + 8);
			setY(myTank.getY() - 16);
			break;
		case 'D':
			setX(myTank.getX() + 8);
			setY(myTank.getY() + 32);
			break;
		case 'L':
			setX(myTank.getX() - 16);
			setY(myTank.getY() + 8);
			break;
		case 'R':
			setX(myTank.getX() + 32);
			setY(myTank.getY() + 8);
		}

		setEmmoIcon();
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

	public ImageIcon getEmmoIcon() {
		return emmoIcon;
	}

	public void setEmmoIcon() {
		switch (this.status) {
		case 'U':
			this.emmoIcon = new ImageIcon(Emmo.class.getResource("/images/emmoUp.png"));
			break;
		case 'D':
			this.emmoIcon = new ImageIcon(Emmo.class.getResource("/images/emmoDown.png"));
			break;
		case 'L':
			this.emmoIcon = new ImageIcon(Emmo.class.getResource("/images/emmoLeft.png"));
			break;
		case 'R':
			this.emmoIcon = new ImageIcon(Emmo.class.getResource("/images/emmoRight.png"));
		}
	}

	public void move() {
	}

	public boolean isDie() {
		return isDie;
	}

	public void setDie(boolean isDie) {
		this.isDie = isDie;
	}

}
