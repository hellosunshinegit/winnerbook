package com.winnerbook.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.activity.dao.ReadBookClubDao;
import com.winnerbook.activity.dto.ReadBookClub;
import com.winnerbook.base.frame.dao.BaseDAO;

@Repository("readBookClubDao")
public class ReadBookClubDaoImpl  extends BaseDAO implements ReadBookClubDao{
	private static final String READBOOKCLUB_MAPPER = "ReadBookClubMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(READBOOKCLUB_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<ReadBookClub> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(READBOOKCLUB_MAPPER + "listByPage", parameter);
	}

	@Override
	public ReadBookClub findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(READBOOKCLUB_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(ReadBookClub readBookClub) {
		return this.sqlSession.insert(READBOOKCLUB_MAPPER+"insert",readBookClub);
	}

	@Override
	public int update(ReadBookClub readBookClub) {
		return this.sqlSession.insert(READBOOKCLUB_MAPPER+"update",readBookClub);
	}

	@Override
	public void delete(Map<String, Object> parameter) {
		this.sqlSession.delete(READBOOKCLUB_MAPPER+"delete",parameter);
	}

	@Override
	public List<Map<String, Object>> getBookClubs(Map<String, Object> parameter) {
		return this.sqlSession.selectList(READBOOKCLUB_MAPPER+"getBookClubs", parameter);
	}

	@Override
	public int getBookClubsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(READBOOKCLUB_MAPPER + "getBookClubsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getReadBookClubDetail(
			Map<String, Object> parameter) {
		return this.sqlSession.selectOne(READBOOKCLUB_MAPPER+"getReadBookClubDetail",parameter);
	}
}
