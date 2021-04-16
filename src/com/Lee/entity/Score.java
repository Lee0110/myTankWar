package com.Lee.entity;

public class Score {
	private Long id;
	private Long grade;
	private String date;
	private Long userId;

	public Score(Long grade, Long userId) {
		super();
		this.grade = grade;
		this.userId = userId;
	}

	public Score(Long grade, String date, Long userId) {
		super();
		this.grade = grade;
		this.date = date;
		this.userId = userId;
	}

	public Score() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
