package com.Lee.entity;

import javax.swing.ImageIcon;

public class Health {

	private int x;
	private int y;
	ImageIcon HealthIcon = new ImageIcon(Health.class.getResource("/images/health.png"));

	public Health() {
		super();
	}

	public Health(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	public ImageIcon getHealthIcon() {
		return HealthIcon;
	}

}
