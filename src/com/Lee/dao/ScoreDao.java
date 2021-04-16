package com.Lee.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Lee.entity.Score;

public interface ScoreDao {

	/**
	 * 
	 * @Title: login
	 * @Desc: 查询排行榜
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	ResultSet getRank(Connection con) throws SQLException;

	/**
	 * 
	 * @Title: saveGrade
	 * @Desc: 存分数
	 * @param con
	 * @param score
	 * @return
	 * @throws Exception
	 */
	int saveGrade(Connection con, Score score) throws Exception;

}