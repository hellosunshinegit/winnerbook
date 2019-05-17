package com.winnerbook.base.frame.dao;

import java.util.List;

public interface JdbcDao {
	//根据sql查询
	public List queryData(String sql);
}
