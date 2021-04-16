package com.Lee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Lee.dao.ScoreDao;
import com.Lee.entity.Score;

public class ScoreDaoImpl implements ScoreDao {

	/**
	 * @Title: login
	 * @Desc: 列出排行榜
	 * @param con
	 * @return
	 * @throws SQLException
	 * @see com.Lee.dao.impl.ScoreDao#login(java.sql.Connection)
	 */
	@Override
	public ResultSet getRank(Connection con) throws SQLException {
		String sql = "SELECT username,grade,DATE FROM score,USER WHERE score.`userId`=user.`id` ORDER BY grade DESC,DATE ASC";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}

	/**
	 * 
	 * @Title: saveGrade
	 * @Desc: 存分数
	 * @param con
	 * @param score
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveGrade(Connection con, Score score) throws Exception {
		String sql = "INSERT INTO score (grade, DATE,userId) VALUES (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, score.getGrade());
		pstmt.setString(2, score.getDate());
		pstmt.setLong(3, score.getUserId());
		return pstmt.executeUpdate();

	}
}
