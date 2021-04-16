package com.Lee.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.Lee.dao.ScoreDao;
import com.Lee.dao.impl.ScoreDaoImpl;
import com.Lee.entity.Score;
import com.Lee.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {
	private ScoreDao sd = new ScoreDaoImpl();

	/**
	 * @Title: getRank
	 * @Desc:
	 * @param con
	 * @return
	 * @throws SQLException
	 * @see com.Lee.service.impl.ScoreService#getRank(java.sql.Connection)
	 */
	@Override
	public ResultSet getRank(Connection con) throws SQLException {
		return sd.getRank(con);
	}

	/**
	 * 
	 * @Title: saveGrade
	 * @Desc:
	 * @param con
	 * @param score
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveGrade(Connection con, Score score) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a = formatter.format(calendar.getTime()).toString();
		score.setDate(a);
		return sd.saveGrade(con, score);

	}
}
