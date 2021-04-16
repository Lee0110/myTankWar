package com.Lee.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class EnemyTank extends Thread {

	private int x;// x坐标
	private int y;// y坐标
	private char status = 'U';// 头的朝向UDLF对应上下左右
	private boolean isDie = false;// 坦克是否死亡
	private ImageIcon enemyTankIcon;
	private List<Emmo> enemyTankEmmoList = new LinkedList<Emmo>();

	@Override
	public void run() {
		try {
			while (!isDie) {
				Random random = new Random();
				int a = random.nextInt(4);// 转的方向
				int b = random.nextInt(10);// 是否转向
				int c = random.nextInt(20);// 是否发射子弹
				if (c < 2) {
					Emmo EnemyEmmo = new Emmo(this);
					EnemyEmmo.start();
					this.enemyTankEmmoList.add(EnemyEmmo);
				}
				if (b < 3) {
					switch (a) {
					case 0:
						if (this.y > 0) {
							this.y -= 16;
						}
						this.status = 'U';
						this.setEnemyTankIcon();
						break;
					case 1:
						if (this.y < 624) {
							this.y += 16;
						}
						this.status = 'D';
						this.setEnemyTankIcon();
						break;
					case 2:
						if (this.x > 0) {
							this.x -= 16;
						}
						this.status = 'L';
						this.setEnemyTankIcon();
						break;
					case 3:
						if (this.x < 752) {
							this.x += 16;
						}
						this.status = 'R';
						this.setEnemyTankIcon();
					}
				} else {
					switch (this.status) {
					case 'U':
						if (this.y > 0) {
							this.y -= 16;
						}
						break;
					case 'D':
						if (this.y < 624) {
							this.y += 16;
						}
						break;
					case 'L':
						if (this.x > 0) {
							this.x -= 16;
						}
						break;
					case 'R':
						if (this.x < 752) {
							this.x += 16;
						}
						break;
					}
				}
				Thread.sleep(84);
				Thread.yield();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public EnemyTank() {
		super();
	}

	public EnemyTank(int x, int y, char status) {
		super();
		this.x = x;
		this.y = y;
		this.status = status;
		this.setEnemyTankIcon();
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

	public boolean isDie() {
		return isDie;
	}

	public void setDie(boolean isDie) {
		this.isDie = isDie;
	}

	public ImageIcon getEnemyTankIcon() {
		return enemyTankIcon;
	}

	public void setEnemyTankIcon() {
		switch (this.status) {
		case 'U':
			this.enemyTankIcon = new ImageIcon(EnemyTank.class.getResource("/images/enemyTankUp.png"));
			break;
		case 'D':
			this.enemyTankIcon = new ImageIcon(EnemyTank.class.getResource("/images/enemyTankDown.png"));
			break;
		case 'L':
			this.enemyTankIcon = new ImageIcon(EnemyTank.class.getResource("/images/enemyTankLeft.png"));
			break;
		case 'R':
			this.enemyTankIcon = new ImageIcon(EnemyTank.class.getResource("/images/enemyTankRight.png"));
		}
	}

	public List<Emmo> getEnemyTankEmmoList() {
		return enemyTankEmmoList;
	}

}
